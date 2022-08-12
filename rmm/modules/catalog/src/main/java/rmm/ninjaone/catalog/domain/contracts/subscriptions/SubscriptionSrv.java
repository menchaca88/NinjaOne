package rmm.ninjaone.catalog.domain.contracts.subscriptions;

import java.util.List;

import rmm.ninjaone.catalog.domain.models.Subscription;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.ServiceSubscription;

public interface SubscriptionSrv {
    List<String> getDeviceTypes();
    List<String> getServiceTypes();

    DeviceSubscription deviceCreate(Subscription.RawData data);
    ServiceSubscription serviceCreate(Subscription.RawData data);
}
