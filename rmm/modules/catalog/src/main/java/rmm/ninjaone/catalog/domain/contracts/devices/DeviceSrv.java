package rmm.ninjaone.catalog.domain.contracts.devices;

import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.models.devices.Device;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;

public interface DeviceSrv {
    Device get(UUID id);
    Device get(Sku sku);
    List<Device> getAll();
    Device create(String name, DeviceSubscription subscription);
    Device update(UUID id, String name);
    Device delete(UUID id);
}
