package rmm.ninjaone.pricing.strategies;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.ports.pricing.Item;

@Component
public class PerDeviceServicePricingStrategy extends BasePricingStrategy<PerDeviceServicePricingStrategy.Data> {

    public PerDeviceServicePricingStrategy(ObjectMapper objectMapper) {
        super(objectMapper, Data.class);
    }

    @Override
    protected double calculate(List<Item> devices, List<Item> services, Item current, Data subscription) {
        var total = devices
            .stream()
            .map(d -> d.getCount())
            .reduce(0, Integer::sum);

        return total * subscription.unitCost;
    }

    @Getter
    @Setter
    public static class Data {
        private double unitCost;
    }
}
