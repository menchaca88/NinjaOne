package rmm.ninjaone.catalog.infrastructure.persistence.strategies;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.models.Subscription;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.PerDeviceSubscription;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;
import rmm.ninjaone.catalog.infrastructure.persistence.data.SubscriptionEntity;

@Component
@RequiredArgsConstructor
public class PerDeviceSubscriptionConverterStrategy implements SubscriptionConverterStrategy<PerDeviceSubscription> {
    private final ObjectMapper objectMapper;

    @Override
    public boolean matches(SubscriptionEntity entity) {
       return entity.getType().equals(PerDeviceSubscription.class.getSimpleName());
    }

    @Override
    public boolean matches(Subscription subscription) {
       return subscription instanceof PerDeviceSubscription;
    }

    @Override
    public PerDeviceSubscription convert(SubscriptionEntity entity) throws IOException {
        if (!matches(entity))
            throw new UnsupportedConversionException(entity.getType());

        var data = objectMapper.readValue(entity.getData(), PerDeviceData.class);

        var subscription = new PerDeviceSubscription(data.getUnitCost());
            
        return subscription;
    }

    @Override
    public SubscriptionEntity convert(Subscription subscription) throws IOException {
        if (!matches(subscription))
            throw new UnsupportedConversionException(subscription.getName());

        var perDevice = (PerDeviceSubscription)subscription;

        var data = new PerDeviceData(perDevice.getUnitCost());
        var raw = objectMapper.writeValueAsString(data);

        var entity = new SubscriptionEntity();

        entity.setType(PerDeviceSubscription.class.getSimpleName());
        entity.setData(raw);

        return entity;
    }
    
    @Data
    class PerDeviceData {
        private final double unitCost;
    }
}
