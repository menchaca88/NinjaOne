package rmm.ninjaone.payments.data;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.payments.domain.models.ServiceCharge;

public class ServiceMother extends ObjectMother {
    public static ServiceCharge random() {
        var service = ServiceCharge.create(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), StringMother.random());

        return service;
    }
}
