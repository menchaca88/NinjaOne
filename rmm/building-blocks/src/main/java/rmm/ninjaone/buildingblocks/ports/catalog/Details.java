package rmm.ninjaone.buildingblocks.ports.catalog;

import java.util.UUID;

import lombok.Value;

@Value
public class Details {
    private final UUID id;
    private final String name;
    private final String sku;
    private final SubscriptionData subscription;
}