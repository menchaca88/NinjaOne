package rmm.ninjaone.catalog.domain.contracts.devices;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.catalog.domain.models.devices.Device;

@org.springframework.stereotype.Repository
public interface DeviceRepository extends Repository<Device> {
    
}
