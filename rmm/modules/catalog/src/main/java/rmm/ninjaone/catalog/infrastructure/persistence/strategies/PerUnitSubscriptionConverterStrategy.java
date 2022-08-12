package rmm.ninjaone.catalog.infrastructure.persistence.strategies;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.models.Subscription;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.PerUnitSubscription;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;
import rmm.ninjaone.catalog.infrastructure.persistence.data.SubscriptionEntity;

@Component
@RequiredArgsConstructor
public class PerUnitSubscriptionConverterStrategy implements SubscriptionConverterStrategy<PerUnitSubscription> {
    private final ObjectMapper objectMapper;

    @Override
    public boolean matches(SubscriptionEntity entity) {
       return entity.getType().equals(PerUnitSubscription.class.getSimpleName());
    }

    @Override
    public boolean matches(Subscription subscription) {
       return subscription instanceof PerUnitSubscription;
    }

    @Override
    public PerUnitSubscription convert(SubscriptionEntity entity) throws IOException {
        if (!matches(entity))
            throw new UnsupportedConversionException(entity.getType());

        var data = objectMapper.readValue(entity.getData(), PerUnitData.class);

        var subscription = new PerUnitSubscription(data.getUnitCost());
            
        return subscription;
    }

    @Override
    public SubscriptionEntity convert(Subscription subscription) throws IOException {
        if (!matches(subscription))
            throw new UnsupportedConversionException(subscription.getName());

        var perUnit = (PerUnitSubscription)subscription;

        var data = new PerUnitData(perUnit.getUnitCost());
        var raw = objectMapper.writeValueAsString(data);

        var entity = new SubscriptionEntity();

        entity.setType(PerUnitSubscription.class.getSimpleName());
        entity.setData(raw);

        return entity;
    }
    
    @Data
    class PerUnitData {
        private final double unitCost;
    }
}
