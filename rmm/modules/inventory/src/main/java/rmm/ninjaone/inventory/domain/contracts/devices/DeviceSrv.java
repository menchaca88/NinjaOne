package rmm.ninjaone.inventory.domain.contracts.devices;

import java.util.List;
import java.util.UUID;

import rmm.ninjaone.inventory.domain.models.Device;

public interface DeviceSrv {
    List<Device> getAll(UUID clientId);
    Device create(UUID clientId, int count, UUID typeId);
    Device update(UUID id, int count);
    Device delete(UUID id);
    int deleteType(UUID typeId);
    int renameType(UUID typeId, String name);
}
