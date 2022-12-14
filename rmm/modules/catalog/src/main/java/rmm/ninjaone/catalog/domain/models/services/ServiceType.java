package rmm.ninjaone.catalog.domain.models.services;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;
import rmm.ninjaone.buildingblocks.domain.events.catalog.ServiceDeletedEvent;
import rmm.ninjaone.buildingblocks.domain.events.catalog.ServiceRenamedEvent;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.ServiceSubscription;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceType extends AggregateRoot {
    private ServiceSubscription subscription;
    private String name;
    private Sku sku;

    private ServiceType(String name, ServiceSubscription subscription) {
        super();

        this.name = name;
        this.subscription = subscription;
        updateSku();
    }

    public String getName() {
        return name;
    }

    public void rename(@NonNull String name) {
        registerEvent(new ServiceRenamedEvent(getId(), this.name, name));
        this.name = name;
        updateSku();
    }

    public ServiceSubscription getSubscription() {
        return subscription;
    }

    public void setSubscription(@NonNull ServiceSubscription subscription) {
        this.subscription = subscription;
        updateSku();
    }

    public Sku getSku() {
        return sku;
    }

    private void updateSku() {
        this.sku = Sku
            .For(ServiceType.class)
            .model(name)
            .price(subscription.getName())
            .build();
    }

    public static ServiceType create(@NonNull String name, @NonNull ServiceSubscription subscription) {
        return new ServiceType(name, subscription);
    }

    @Override
    protected void deleted() {
        registerEvent(new ServiceDeletedEvent(getId()));
    }
}
