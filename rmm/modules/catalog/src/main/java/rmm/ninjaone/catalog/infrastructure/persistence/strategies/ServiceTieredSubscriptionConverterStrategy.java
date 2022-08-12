package rmm.ninjaone.catalog.infrastructure.persistence.strategies;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.models.Subscription;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.TieredSubscription;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;
import rmm.ninjaone.catalog.infrastructure.persistence.data.SubscriptionEntity;

@Component
@RequiredArgsConstructor
public class ServiceTieredSubscriptionConverterStrategy implements SubscriptionConverterStrategy<TieredSubscription> {
    private final ObjectMapper objectMapper;

    @Override
    public boolean matches(SubscriptionEntity entity) {
       return entity.getType().equals(TieredSubscription.class.getSimpleName());
    }

    @Override
    public boolean matches(Subscription subscription) {
       return subscription instanceof TieredSubscription;
    }

    @Override
    public TieredSubscription convert(SubscriptionEntity entity) throws IOException {
        if (!matches(entity))
            throw new UnsupportedConversionException(entity.getType());

        var data = objectMapper.readValue(entity.getData(), TieredData.class);

        var subscription = new TieredSubscription(data
            .getTiers()
            .stream()
            .map(t -> new TieredSubscription.Tier(t.getCount(), t.getCost()))
            .toList());
            
        return subscription;
    }

    @Override
    public SubscriptionEntity convert(Subscription subscription) throws IOException {
        if (!matches(subscription))
            throw new UnsupportedConversionException(subscription.getName());

        var tiered = (TieredSubscription)subscription;

        var data = new TieredData(tiered
            .getTiers()
            .stream()
            .map(t -> new TieredData.Tier(t.getCount(), t.getCost()))
            .toList());
        var raw = objectMapper.writeValueAsString(data);

        var entity = new SubscriptionEntity();

        entity.setType(TieredSubscription.class.getSimpleName());
        entity.setData(raw);

        return entity;
    }
    
    @Data
    class TieredData {
        private final List<Tier> tiers;

        @Data
        static class Tier {
            private final int count;
            private final double cost;
        }
    }
}
