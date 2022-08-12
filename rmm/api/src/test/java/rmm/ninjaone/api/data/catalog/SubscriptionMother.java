package rmm.ninjaone.api.data.catalog;

import java.util.HashMap;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.catalog.infrastructure.endpoints.subscriptions.SubscriptionRequest;

public class SubscriptionMother extends ObjectMother {
    public static SubscriptionRequest device() {
        var request = new SubscriptionRequest();

        var map = new HashMap<String, Object>();
        map.put("monthlyCost", 11);

        request.setType("MonthlyFixedDeviceSubscription");
        request.setData(map);

        return request;
    }

    public static SubscriptionRequest service() {
        var request = new SubscriptionRequest();

        var map = new HashMap<String, Object>();
        map.put("monthlyCost", 11);

        request.setType("MonthlyFixedServiceSubscription");
        request.setData(map);

        return request;
    }
}
