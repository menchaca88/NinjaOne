package rmm.ninjaone.catalog.infrastructure.strategies;

import java.io.IOException;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.subscriptions.SubscriptionConverterStrategy;
import rmm.ninjaone.catalog.domain.models.Subscription;
import rmm.ninjaone.catalog.domain.models.Subscription.RawData;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;
import rmm.ninjaone.catalog.infrastructure.persistence.converters.PersistentSubscriptionConverterStrategy;
import rmm.ninjaone.catalog.infrastructure.persistence.converters.SubscriptionEntity;

@RequiredArgsConstructor
public abstract class BaseSubscriptionConverterStrategy<T extends Subscription, D> implements PersistentSubscriptionConverterStrategy<T>, SubscriptionConverterStrategy<T> {
    private final ObjectMapper objectMapper;
    private final Validator validator;
    private final Class<T> subscriptionClazz;
    private final Class<D> dataClazz;

    @Override
    public boolean matches(RawData data) {
        return data.getType().equals(subscriptionClazz.getSimpleName());
    }

    @Override
    public boolean matches(SubscriptionEntity data) {
        return data.getType().equals(subscriptionClazz.getSimpleName());
    }

    @Override
    public boolean matches(Subscription subscription) {
        return subscription.getClass().equals(subscriptionClazz);
    }

    @Override
    public T convert(RawData data) {
        if (!matches(data))
            throw new UnsupportedConversionException(data.getType());

        var raw = objectMapper.convertValue(data.getData(), dataClazz);
        validate(raw);

        return convertToSubscription(raw);
    }

    @Override
    @SuppressWarnings("unchecked")
    public RawData convertToRaw(Subscription subscription) {
        if (!matches(subscription))
            throw new UnsupportedConversionException(subscription.getName());

        var sub = (T)subscription;

        Map<String, Object> raw = objectMapper.convertValue(sub, Map.class);
        
        var data = new RawData();

        data.setType(subscription.getName());
        data.setData(raw);

        return data;
    }

    @Override
    public T convert(SubscriptionEntity entity) throws IOException {
        if (!matches(entity))
            throw new UnsupportedConversionException(entity.getType());

        var data = objectMapper.readValue(entity.getData(), dataClazz);

        return convertToSubscription(data);
    }

    protected abstract T convertToSubscription(D entity);

    @Override
    @SuppressWarnings("unchecked")
    public SubscriptionEntity convert(Subscription subscription) throws IOException {
        if (!matches(subscription))
            throw new UnsupportedConversionException(subscription.getName());

        var sub = (T)subscription;

        var data = convertToData(sub);
        var raw = objectMapper.writeValueAsString(data);

        var entity = new SubscriptionEntity();

        entity.setType(subscriptionClazz.getSimpleName());
        entity.setData(raw);

        return entity;
    }

    protected abstract D convertToData(T subcription);

    private void validate(D data) {
        var violations = validator.validate(data);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
