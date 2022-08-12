package rmm.ninjaone.catalog.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.catalog.domain.models.services.Service;

public class ServiceMother extends ObjectMother {
    public static Service random() {
        var service = Service.create(StringMother.random(), SubscriptionMother.serviceRandom());

        return service;
    }

    public static List<Service> count(Integer count) {
        List<Service> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
            list.add(random());

        return list;
    }

    public static Service withId(UUID id) {
        var service = random();
        service.setId(id);

        return service;
    }
}
