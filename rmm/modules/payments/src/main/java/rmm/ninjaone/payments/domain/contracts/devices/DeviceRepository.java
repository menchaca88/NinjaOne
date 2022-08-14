package rmm.ninjaone.payments.domain.contracts.devices;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.payments.domain.models.DeviceCharge;

@org.springframework.stereotype.Repository("DeviceChargeRepository")
public interface DeviceRepository extends Repository<DeviceCharge> {
    
}