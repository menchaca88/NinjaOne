package rmm.ninjaone.buildingblocks.domain.events.inventory;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class DeviceCreatedEvent extends Event {
    @Getter private UUID clientId;
    @Getter private int count;

    public DeviceCreatedEvent(UUID entityId, int count, UUID clientId) {
        super(entityId);
        this.count = count;
        this.clientId = clientId;
    }
}

