package rmm.ninjaone.catalog.domain.models.devices;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;
import rmm.ninjaone.buildingblocks.domain.events.catalog.DeviceDeletedEvent;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Device extends AggregateRoot {
    private DeviceSubscription subscription;
    private String name;
    private Sku sku;

    private Device(String name, DeviceSubscription subscription) {
        super();

        this.name = name;
        this.subscription = subscription;
        updateSku();
    }

    public String getName() {
        return name;
    }

    public void rename(@NonNull String name) {
        this.name = name;
        updateSku();
    }

    public DeviceSubscription getSubscription() {
        return subscription;
    }

    public void setSubscription(@NonNull DeviceSubscription subscription) {
        this.subscription = subscription;
        updateSku();
    }

    public Sku getSku() {
        return sku;
    }

    private void updateSku() {
        this.sku = Sku
            .For(Device.class)
            .model(name)
            .price(subscription.getName())
            .build();
    }

    public static Device create(@NonNull String name, @NonNull DeviceSubscription subscription) {
        return new Device(name, subscription);
    }

    @Override
    protected void deleted() {
        registerEvent(new DeviceDeletedEvent(getId()));
    }
}
