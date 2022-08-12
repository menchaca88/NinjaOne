package rmm.ninjaone.api.data.catalog;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.catalog.infrastructure.endpoints.devices.CreateDeviceRequest;

public class CreateDeviceMother extends ObjectMother {
    public static CreateDeviceRequest valid() {
        var request = new CreateDeviceRequest();

        request.setName(StringMother.random());
        request.setSubscription(SubscriptionMother.device());

        return request;
    }
}
