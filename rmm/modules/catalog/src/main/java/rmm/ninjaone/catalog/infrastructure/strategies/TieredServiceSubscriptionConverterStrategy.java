package rmm.ninjaone.catalog.infrastructure.strategies;

import java.util.List;

import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.TieredServiceSubscription;

@Component
public class TieredServiceSubscriptionConverterStrategy extends BaseSubscriptionConverterStrategy<TieredServiceSubscription, TieredServiceSubscriptionConverterStrategy.TieredData> {
    public TieredServiceSubscriptionConverterStrategy(ObjectMapper objectMapper, Validator validator) {
        super(objectMapper, validator, TieredServiceSubscription.class, TieredData.class);
    }

    @Override
    protected TieredServiceSubscription convertToSubscription(TieredData data) {
        return new TieredServiceSubscription(data
            .getTiers()
            .stream()
            .map(t -> new TieredServiceSubscription.Tier(t.getCount(), t.getCost()))
            .toList(), data.getLastCost());
    }

    @Override
    protected TieredData convertToData(TieredServiceSubscription subcription) {
        return new TieredData(subcription
            .getTiers()
            .stream()
            .map(t -> new TieredData.Tier(t.getCount(), t.getCost()))
            .toList(), subcription.getLastCost());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TieredData {
        @NotEmpty private List<Tier> tiers;
        @Min(0) private double lastCost;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Tier {
            @Min(0) private int count;
            @Min(0) private double cost;
        }
    }
}
