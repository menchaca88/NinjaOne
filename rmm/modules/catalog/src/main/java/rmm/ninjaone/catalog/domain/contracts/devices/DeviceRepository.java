package rmm.ninjaone.catalog.domain.contracts.devices;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.catalog.domain.models.devices.DeviceType;

@org.springframework.stereotype.Repository("DeviceTypeRepository")
public interface DeviceRepository extends Repository<DeviceType> {
    
}
