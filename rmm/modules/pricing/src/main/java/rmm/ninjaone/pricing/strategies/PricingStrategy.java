package rmm.ninjaone.pricing.strategies;

import lombok.Value;
import rmm.ninjaone.buildingblocks.ports.catalog.SubscriptionData;
import rmm.ninjaone.buildingblocks.ports.pricing.Item;
import rmm.ninjaone.buildingblocks.ports.pricing.Package;

public interface PricingStrategy {
    boolean supports(DetailedItem item);
    double calculate(Package pckg, DetailedItem item);

    @Value
    public static class DetailedItem {
        private final Item item;
        private final SubscriptionData subscription;
    }
}
