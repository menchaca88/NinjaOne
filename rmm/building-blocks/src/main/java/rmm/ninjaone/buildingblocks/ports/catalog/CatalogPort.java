package rmm.ninjaone.buildingblocks.ports.catalog;

import java.util.UUID;

public interface CatalogPort {
    DeviceDetails findDevice(UUID id);
    ServiceDetails findService(UUID id);
}
