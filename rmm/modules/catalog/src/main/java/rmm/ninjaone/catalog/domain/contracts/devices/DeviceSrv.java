package rmm.ninjaone.catalog.domain.contracts.devices;

import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.models.devices.DeviceType;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;

public interface DeviceSrv {
    DeviceType get(UUID id);
    DeviceType get(Sku sku);
    List<DeviceType> getAll();
    DeviceType create(String name, DeviceSubscription subscription);
    DeviceType update(UUID id, String name);
    DeviceType delete(UUID id);
}
