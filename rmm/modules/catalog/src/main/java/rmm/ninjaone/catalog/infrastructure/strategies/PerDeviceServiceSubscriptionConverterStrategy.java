package rmm.ninjaone.catalog.infrastructure.strategies;

import javax.validation.Validator;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.PerDeviceServiceSubscription;

@Component
public class PerDeviceServiceSubscriptionConverterStrategy extends BaseSubscriptionConverterStrategy<PerDeviceServiceSubscription, PerDeviceServiceSubscriptionConverterStrategy.PerDeviceData> {

    public PerDeviceServiceSubscriptionConverterStrategy(ObjectMapper objectMapper, Validator validator) {
        super(objectMapper, validator, PerDeviceServiceSubscription.class, PerDeviceData.class);
    }

    @Override
    protected PerDeviceServiceSubscription convertToSubscription(PerDeviceData data) {
        return new PerDeviceServiceSubscription(data.getUnitCost());
    }

    @Override
    protected PerDeviceData convertToData(PerDeviceServiceSubscription subcription) {
        return new PerDeviceData(subcription.getUnitCost());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PerDeviceData {
        @Min(0) private double unitCost;
    }
}
