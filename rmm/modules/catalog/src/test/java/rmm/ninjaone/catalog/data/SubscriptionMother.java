package rmm.ninjaone.catalog.data;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.ServiceSubscription;

public class SubscriptionMother extends ObjectMother {
    public static DeviceSubscription deviceRandom() {
       return deviceMonthlyFixed();
    }

    public static DeviceSubscription deviceMonthlyFixed() {
        var subscription = new rmm.ninjaone.catalog.domain.models.devices.subscriptions.MonthlyFixedSubscription(random.nextDouble());

        return subscription;
    }

    public static ServiceSubscription serviceRandom() {
        return serviceMonthlyFixed();
    }
 
     public static ServiceSubscription serviceMonthlyFixed() {
        var subscription = new rmm.ninjaone.catalog.domain.models.services.subscriptions.MonthlyFixedSubscription(random.nextDouble());

        return subscription;
    }
}
