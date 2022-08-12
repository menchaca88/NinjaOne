package rmm.ninjaone.buildingblocks.ports.catalog;

import java.util.UUID;

public interface CatalogPort {
    boolean existsDevice(UUID id);
    boolean existsService(UUID id);
}
