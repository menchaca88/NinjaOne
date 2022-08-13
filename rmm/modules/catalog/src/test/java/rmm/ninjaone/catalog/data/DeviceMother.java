package rmm.ninjaone.catalog.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.catalog.domain.models.devices.DeviceType;

public class DeviceMother extends ObjectMother {
    public static DeviceType random() {
        var device = DeviceType.create(StringMother.random(), SubscriptionMother.deviceRandom());

        return device;
    }

    public static List<DeviceType> count(Integer count) {
        List<DeviceType> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
            list.add(random());

        return list;
    }

    public static DeviceType withId(UUID id) {
        var device = random();
        device.setId(id);

        return device;
    }
}
