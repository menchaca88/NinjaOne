package rmm.ninjaone.buildingblocks.ports.catalog;

import java.util.UUID;

import lombok.Value;

@Value
public class ServiceDetails {
    private final UUID id;
    private final String name;
    private final String sku;
}
