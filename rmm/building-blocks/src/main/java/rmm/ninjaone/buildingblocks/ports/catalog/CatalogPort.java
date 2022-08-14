package rmm.ninjaone.buildingblocks.ports.catalog;

import java.util.UUID;

public interface CatalogPort {
    Details findDevice(UUID id);
    Details findService(UUID id);
}
