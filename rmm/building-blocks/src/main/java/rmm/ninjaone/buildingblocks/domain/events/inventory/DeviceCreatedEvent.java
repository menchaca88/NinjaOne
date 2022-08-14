package rmm.ninjaone.buildingblocks.domain.events.inventory;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class DeviceCreatedEvent extends Event {
    @Getter private UUID clientId;
    @Getter private UUID typeId;
    @Getter private String typeName;
    @Getter private int count;

    public DeviceCreatedEvent(UUID entityId, int count, UUID clientId, UUID typeId, String typeName) {
        super(entityId);
        this.count = count;
        this.clientId = clientId;
        this.typeId = typeId;
        this.typeName = typeName;
    }
}

