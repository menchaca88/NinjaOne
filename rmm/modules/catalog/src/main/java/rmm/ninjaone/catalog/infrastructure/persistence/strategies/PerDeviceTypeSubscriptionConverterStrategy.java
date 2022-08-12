package rmm.ninjaone.catalog.infrastructure.persistence.strategies;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.models.Subscription;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.PerDeviceTypeSubscription;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;
import rmm.ninjaone.catalog.infrastructure.persistence.data.SubscriptionEntity;

@Component
@RequiredArgsConstructor
public class PerDeviceTypeSubscriptionConverterStrategy implements SubscriptionConverterStrategy<PerDeviceTypeSubscription> {
    private final ObjectMapper objectMapper;

    @Override
    public boolean matches(SubscriptionEntity entity) {
       return entity.getType().equals(PerDeviceTypeSubscription.class.getSimpleName());
    }

    @Override
    public boolean matches(Subscription subscription) {
       return subscription instanceof PerDeviceTypeSubscription;
    }

    @Override
    public PerDeviceTypeSubscription convert(SubscriptionEntity entity) throws IOException {
        if (!matches(entity))
            throw new UnsupportedConversionException(entity.getType());

        var data = objectMapper.readValue(entity.getData(), PerDeviceTypeData.class);

        var subscription = new PerDeviceTypeSubscription(data
            .getTypes()
            .stream()
            .map(t -> new PerDeviceTypeSubscription.Type(t.getDeviceId(), t.getCost()))
            .toList());
            
        return subscription;
    }

    @Override
    public SubscriptionEntity convert(Subscription subscription) throws IOException {
        if (!matches(subscription))
            throw new UnsupportedConversionException(subscription.getName());

        var perDeviceType = (PerDeviceTypeSubscription)subscription;

        var data = new PerDeviceTypeData(perDeviceType
            .getTypes()
            .stream()
            .map(t -> new PerDeviceTypeData.Type(t.getDeviceId(), t.getCost()))
            .toList());
        var raw = objectMapper.writeValueAsString(data);

        var entity = new SubscriptionEntity();

        entity.setType(PerDeviceTypeSubscription.class.getSimpleName());
        entity.setData(raw);

        return entity;
    }
    
    @Data
    class PerDeviceTypeData {
        private final List<Type> types;

        @Data
        static class Type {
            private final UUID deviceId;
            private final double cost;
        }
    }
}
