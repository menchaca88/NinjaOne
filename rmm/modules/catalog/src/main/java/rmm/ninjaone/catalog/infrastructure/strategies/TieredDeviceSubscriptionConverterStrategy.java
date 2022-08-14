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
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.TieredDeviceSubscription;

@Component
public class TieredDeviceSubscriptionConverterStrategy extends BaseSubscriptionConverterStrategy<TieredDeviceSubscription, TieredDeviceSubscriptionConverterStrategy.TieredData> {
    public TieredDeviceSubscriptionConverterStrategy(ObjectMapper objectMapper, Validator validator) {
        super(objectMapper, validator, TieredDeviceSubscription.class, TieredData.class);
    }

    @Override
    protected TieredDeviceSubscription convertToSubscription(TieredData data) {
        return new TieredDeviceSubscription(data
            .getTiers()
            .stream()
            .map(t -> new TieredDeviceSubscription.Tier(t.getCount(), t.getCost()))
            .toList(), data.getLastCost());
    }

    @Override
    protected TieredData convertToData(TieredDeviceSubscription subcription) {
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
        static class Tier {
            @Min(0) private int count;
            @Min(0) private double cost;
        }
    }
}
