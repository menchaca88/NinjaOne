package rmm.ninjaone.buildingblocks.domain.events.inventory;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class ServiceCreatedEvent extends Event {
    @Getter private UUID clientId;

    public ServiceCreatedEvent(UUID entityId, UUID clientId) {
        super(entityId);
        this.clientId = clientId;
    }
}
