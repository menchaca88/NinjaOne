package rmm.ninjaone.pricing.strategies;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.ports.pricing.Item;

@Component
public class TieredDevicePricingStrategy extends BasePricingStrategy<TieredDevicePricingStrategy.Data> {

    public TieredDevicePricingStrategy(ObjectMapper objectMapper) {
        super(objectMapper, Data.class);
    }

    @Override
    protected double calculate(List<Item> devices, List<Item> services, Item current, Data subscription) {
        for (var tier : subscription.getTiers())
            if (tier.getCount() >= current.getCount())
                return current.getCount() * tier.getCost();

        return current.getCount() * subscription.getLastCost();
    }

    @Getter
    @Setter
    public static class Data {
        private List<Tier> tiers;
        private double lastCost;
    
        @Getter
        @Setter
        public static class Tier {
            private int count;
            private double cost;
        }
    }
}
