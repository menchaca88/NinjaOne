package rmm.ninjaone.payments.data;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.payments.domain.models.DeviceCharge;

public class DeviceMother extends ObjectMother {
    public static DeviceCharge random() {
        var device = DeviceCharge.create(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), StringMother.random(), 100);

        return device;
    }
}
