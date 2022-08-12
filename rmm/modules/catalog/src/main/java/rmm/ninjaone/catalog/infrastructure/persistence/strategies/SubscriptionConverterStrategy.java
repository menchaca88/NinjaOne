package rmm.ninjaone.catalog.infrastructure.persistence.strategies;

import java.io.IOException;

import rmm.ninjaone.catalog.domain.models.Subscription;
import rmm.ninjaone.catalog.infrastructure.persistence.data.SubscriptionEntity;

public interface SubscriptionConverterStrategy<T extends Subscription> {
    boolean matches(SubscriptionEntity entity);
    boolean matches(Subscription subscription);
    T convert(SubscriptionEntity entity) throws IOException;
    SubscriptionEntity convert(Subscription subscription) throws IOException;
}
