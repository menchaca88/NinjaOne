package rmm.ninjaone.inventory.domain.models;

import java.util.UUID;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;
import rmm.ninjaone.buildingblocks.domain.events.inventory.DeviceCountUpdatedEvent;
import rmm.ninjaone.buildingblocks.domain.events.inventory.DeviceCreatedEvent;
import rmm.ninjaone.buildingblocks.domain.events.inventory.DeviceDeletedEvent;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Device extends AggregateRoot {
    private @Type(type="uuid-char") UUID clientId;
    private @Type(type="uuid-char") UUID typeId;
    private String TypeName;
    private int count;

    private Device(int count, UUID clientId, UUID typeId, String typeName) {
        super();

        this.count = count;
        this.clientId = clientId;
        this.typeId = typeId;
        this.TypeName = typeName;

        registerEvent(new DeviceCreatedEvent(getId(), count, clientId, typeId, typeName));
    }

    public UUID getClientId() {
        return clientId;
    }

    public int getCount() {
        return count;
    }

    public void updateCount(int count) {
        if (count <= 0)
            throw new IllegalArgumentException("count");

        registerEvent(new DeviceCountUpdatedEvent(getId(), clientId, this.count, count));
        this.count = count;
    }

    public UUID getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(@NonNull String typeName) {
        this.TypeName = typeName;
    }

    public static Device create(int count, @NonNull UUID clientId, @NonNull UUID typeId, @NonNull String typeName) {
        if (count <= 0)
            throw new IllegalArgumentException("count");

        return new Device(count, clientId, typeId, typeName);
    }

    @Override
    protected void deleted() {
        registerEvent(new DeviceDeletedEvent(getId(), clientId));
    }
}
