package rmm.ninjaone.catalog.infrastructure.persistence.strategies;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.models.Subscription;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.MonthlyFixedSubscription;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;
import rmm.ninjaone.catalog.infrastructure.persistence.data.SubscriptionEntity;

@Component
@RequiredArgsConstructor
public class DeviceMonthlyFixedSubscriptionConverterStrategy implements SubscriptionConverterStrategy<MonthlyFixedSubscription> {
    private final ObjectMapper objectMapper;

    @Override
    public boolean matches(SubscriptionEntity entity) {
       return entity.getType().equals(MonthlyFixedSubscription.class.getSimpleName());
    }

    @Override
    public boolean matches(Subscription subscription) {
       return subscription instanceof MonthlyFixedSubscription;
    }

    @Override
    public MonthlyFixedSubscription convert(SubscriptionEntity entity) throws IOException {
        if (!matches(entity))
            throw new UnsupportedConversionException(entity.getType());

        var data = objectMapper.readValue(entity.getData(), MonthlyFixedData.class);

        var subscription = new MonthlyFixedSubscription(data.getMonthlyCost());
            
        return subscription;
    }

    @Override
    public SubscriptionEntity convert(Subscription subscription) throws IOException {
        if (!matches(subscription))
            throw new UnsupportedConversionException(subscription.getName());

        var monthlyFixed = (MonthlyFixedSubscription)subscription;

        var data = new MonthlyFixedData(monthlyFixed.getMonthlyCost());
        var raw = objectMapper.writeValueAsString(data);

        var entity = new SubscriptionEntity();

        entity.setType(MonthlyFixedSubscription.class.getSimpleName());
        entity.setData(raw);

        return entity;
    }
    
    @Data
    class MonthlyFixedData {
        private final double monthlyCost; 
    }
}
