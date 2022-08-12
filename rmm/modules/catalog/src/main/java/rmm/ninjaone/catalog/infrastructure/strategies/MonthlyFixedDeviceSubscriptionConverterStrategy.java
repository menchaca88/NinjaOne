package rmm.ninjaone.catalog.infrastructure.strategies;

import javax.validation.Validator;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.MonthlyFixedDeviceSubscription;

@Component
public class MonthlyFixedDeviceSubscriptionConverterStrategy extends BaseSubscriptionConverterStrategy<MonthlyFixedDeviceSubscription, MonthlyFixedDeviceSubscriptionConverterStrategy.MonthlyFixedData> {

    public MonthlyFixedDeviceSubscriptionConverterStrategy(ObjectMapper objectMapper, Validator validator) {
        super(objectMapper, validator, MonthlyFixedDeviceSubscription.class, MonthlyFixedData.class);
    }

    @Override
    protected MonthlyFixedDeviceSubscription convertToSubscription(MonthlyFixedData data) {
        return new MonthlyFixedDeviceSubscription(data.getMonthlyCost());
    }

    @Override
    protected MonthlyFixedData convertToData(MonthlyFixedDeviceSubscription subcription) {
        return new MonthlyFixedData(subcription.getMonthlyCost());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MonthlyFixedData {
        @Min(0) private double monthlyCost; 
    }
}
