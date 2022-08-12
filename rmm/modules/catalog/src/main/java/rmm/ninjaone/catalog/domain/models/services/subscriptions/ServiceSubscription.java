package rmm.ninjaone.catalog.domain.models.services.subscriptions;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import rmm.ninjaone.catalog.domain.models.Subscription;

public abstract class ServiceSubscription implements Subscription {
    public boolean relatedTo(UUID deviceId) {
        return false;
    }

    public List<UUID> getRelatedDevices() {
        return Collections.emptyList();
    }
}
