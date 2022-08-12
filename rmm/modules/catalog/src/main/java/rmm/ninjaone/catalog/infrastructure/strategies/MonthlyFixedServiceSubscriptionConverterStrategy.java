package rmm.ninjaone.catalog.infrastructure.strategies;

import javax.validation.Validator;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.MonthlyFixedServiceSubscription;

@Component
public class MonthlyFixedServiceSubscriptionConverterStrategy extends BaseSubscriptionConverterStrategy<MonthlyFixedServiceSubscription, MonthlyFixedServiceSubscriptionConverterStrategy.MonthlyFixedData> {

    public MonthlyFixedServiceSubscriptionConverterStrategy(ObjectMapper objectMapper, Validator validator) {
        super(objectMapper, validator, MonthlyFixedServiceSubscription.class, MonthlyFixedData.class);
    }

    @Override
    protected MonthlyFixedServiceSubscription convertToSubscription(MonthlyFixedData data) {
        return new MonthlyFixedServiceSubscription(data.getMonthlyCost());
    }

    @Override
    protected MonthlyFixedData convertToData(MonthlyFixedServiceSubscription subcription) {
        return new MonthlyFixedData(subcription.getMonthlyCost());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MonthlyFixedData {
        @Min(0) private double monthlyCost; 
    }
}
