package rmm.ninjaone.api.data.catalog;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest;

public class CreateServiceMother extends ObjectMother {
    public static CreateServiceRequest valid() {
        var request = new CreateServiceRequest();

        request.setName(StringMother.random());
        request.setSubscription(SubscriptionMother.service());

        return request;
    }
}
