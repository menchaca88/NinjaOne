package rmm.ninjaone.inventory.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.buildingblocks.ports.catalog.ServiceDetails;
import rmm.ninjaone.inventory.domain.models.Service;

public class ServiceMother extends ObjectMother {
    public static Service random() {
        var service = Service.create(UUID.randomUUID(), UUID.randomUUID(), StringMother.random());

        return service;
    }

    public static ServiceDetails randomType() {
        var type = new ServiceDetails(UUID.randomUUID(), StringMother.random(), StringMother.random());

        return type;
    }

    public static List<Service> countWithClient(UUID clientId, Integer count) {
        List<Service> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
            list.add(withClient(clientId));

        return list;
    }

    public static Service withClient(UUID clientId) {
        var service = Service.create(clientId, UUID.randomUUID(), StringMother.random());

        return service;
    }
}
