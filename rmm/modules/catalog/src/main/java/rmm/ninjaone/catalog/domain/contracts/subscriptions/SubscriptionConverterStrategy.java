package rmm.ninjaone.catalog.domain.contracts.subscriptions;

import rmm.ninjaone.catalog.domain.models.Subscription;
import rmm.ninjaone.catalog.domain.models.Subscription.RawData;

public interface SubscriptionConverterStrategy<T extends Subscription> {
    boolean matches(RawData data);
    T convert(RawData data);
}
