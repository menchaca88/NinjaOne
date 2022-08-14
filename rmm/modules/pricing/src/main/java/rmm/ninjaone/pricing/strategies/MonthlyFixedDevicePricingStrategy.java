package rmm.ninjaone.pricing.strategies;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.ports.pricing.Item;

@Component
public class MonthlyFixedDevicePricingStrategy extends BasePricingStrategy<MonthlyFixedDevicePricingStrategy.Data> {

    public MonthlyFixedDevicePricingStrategy(ObjectMapper objectMapper) {
        super(objectMapper, Data.class);
    }

    @Override
    protected double calculate(List<Item> devices, List<Item> services, Item current, Data subscription) {
        return subscription.monthlyCost;
    }

    @Getter
    @Setter
    public static class Data {
        private double monthlyCost;
    }
}
