package rmm.ninjaone.inventory.domain.models;

import java.util.UUID;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;
import rmm.ninjaone.buildingblocks.domain.events.inventory.ServiceCreatedEvent;
import rmm.ninjaone.buildingblocks.domain.events.inventory.ServiceDeletedEvent;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Service extends AggregateRoot {
    private @Type(type="uuid-char") UUID clientId;
    private @Type(type="uuid-char") UUID typeId;
    private String TypeName;

    private Service(UUID clientId, UUID typeId, String typeName) {
        super();

        this.clientId = clientId;
        this.typeId = typeId;
        this.TypeName = typeName;

        registerEvent(new ServiceCreatedEvent(getId(), clientId));
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(@NonNull UUID clientId) {
        this.clientId = clientId;
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

    public static Service create(@NonNull UUID clientId, @NonNull UUID typeId, @NonNull String typeName) {
        return new Service(clientId, typeId, typeName);
    }

    @Override
    protected void deleted() {
        registerEvent(new ServiceDeletedEvent(getId(), clientId));
    }
}
