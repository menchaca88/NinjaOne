package rmm.ninjaone.catalog.infrastructure.strategies;

import javax.validation.Validator;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.PerUnitDeviceSubscription;

@Component
public class PerUnitDeviceSubscriptionConverterStrategy extends BaseSubscriptionConverterStrategy<PerUnitDeviceSubscription, PerUnitDeviceSubscriptionConverterStrategy.PerUnitData> {

    public PerUnitDeviceSubscriptionConverterStrategy(ObjectMapper objectMapper, Validator validator) {
        super(objectMapper, validator, PerUnitDeviceSubscription.class, PerUnitData.class);
    }

    @Override
    protected PerUnitDeviceSubscription convertToSubscription(PerUnitData data) {
        return new PerUnitDeviceSubscription(data.getUnitCost());
    }

    @Override
    protected PerUnitData convertToData(PerUnitDeviceSubscription subcription) {
        return new PerUnitData(subcription.getUnitCost());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PerUnitData {
        @Min(0) private double unitCost;
    }
}
