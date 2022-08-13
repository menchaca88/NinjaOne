package rmm.ninjaone.inventory.domain.contracts.devices;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.inventory.domain.models.Device;

@org.springframework.stereotype.Repository("DeviceRepository")
public interface DeviceRepository extends Repository<Device> {
    
}
