package rmm.ninjaone.buildingblocks.domain.events.inventory;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class ServiceCreatedEvent extends Event {
    @Getter private UUID clientId;
    @Getter private UUID typeId;
    @Getter private String typeName;

    public ServiceCreatedEvent(UUID entityId, UUID clientId, UUID typeId, String typeName) {
        super(entityId);
        this.clientId = clientId;
        this.typeId = typeId;
        this.typeName = typeName;
    }
}
