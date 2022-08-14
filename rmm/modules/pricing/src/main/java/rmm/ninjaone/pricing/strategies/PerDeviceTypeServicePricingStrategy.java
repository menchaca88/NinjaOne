package rmm.ninjaone.pricing.strategies;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.ports.pricing.Item;

@Component
public class PerDeviceTypeServicePricingStrategy extends BasePricingStrategy<PerDeviceTypeServicePricingStrategy.Data> {

    public PerDeviceTypeServicePricingStrategy(ObjectMapper objectMapper) {
        super(objectMapper, Data.class);
    }

    @Override
    protected double calculate(List<Item> devices, List<Item> services, Item current, Data subscription) {
        var costs = subscription
            .getTypes()
            .stream()
            .collect(Collectors.toMap(Data.Type::getDeviceId, Data.Type::getCost));

        var total = 0.0;
        for (var item : devices) 
            if (costs.containsKey(item.getTypeId()))
                total += item.getCount() * costs.get(item.getTypeId());

        return total;
    }

    @Getter
    @Setter
    public static class Data {
        private List<Type> types;

        @Getter
        @Setter
        public static class Type {
            private UUID deviceId;
            private double cost;
        }
    }
}
