package rmm.ninjaone.catalog.infrastructure.persistence.converters;

import java.io.IOException;

import rmm.ninjaone.catalog.domain.models.Subscription;

public interface PersistentSubscriptionConverterStrategy<T extends Subscription> {
    boolean matches(SubscriptionEntity data);
    boolean matches(Subscription subscription);
    T convert(SubscriptionEntity data) throws IOException;
    SubscriptionEntity convert(Subscription subscription) throws IOException;
}
