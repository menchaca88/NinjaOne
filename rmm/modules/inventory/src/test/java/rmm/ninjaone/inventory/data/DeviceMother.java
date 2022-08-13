package rmm.ninjaone.inventory.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.buildingblocks.ports.catalog.DeviceDetails;
import rmm.ninjaone.inventory.domain.models.Device;

public class DeviceMother extends ObjectMother {
    public static Device random() {
        var device = Device.create(random.nextInt(100), UUID.randomUUID(), UUID.randomUUID(), StringMother.random());

        return device;
    }

    public static DeviceDetails randomType() {
        var type = new DeviceDetails(UUID.randomUUID(), StringMother.random(), StringMother.random());

        return type;
    }

    public static List<Device> countWithClient(UUID clientId, Integer count) {
        List<Device> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
            list.add(withClient(clientId));

        return list;
    }

    public static Device withClient(UUID clientId) {
        var device = Device.create(random.nextInt(100), clientId, UUID.randomUUID(), StringMother.random());

        return device;
    }
}
