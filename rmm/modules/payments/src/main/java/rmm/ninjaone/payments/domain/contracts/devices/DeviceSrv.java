package rmm.ninjaone.payments.domain.contracts.devices;

import java.util.UUID;

import rmm.ninjaone.payments.domain.models.DeviceCharge;

public interface DeviceSrv {
    DeviceCharge create(UUID id, UUID payerId, int count, UUID typeId, String typeName);
    DeviceCharge update(UUID id, int count);
    DeviceCharge delete(UUID id);
    int deleteType(UUID typeId);
    int renameType(UUID typeId, String name);
}
