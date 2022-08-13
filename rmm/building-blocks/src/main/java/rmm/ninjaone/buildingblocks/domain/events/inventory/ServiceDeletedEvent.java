package rmm.ninjaone.buildingblocks.domain.events.inventory;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class ServiceDeletedEvent extends Event {
    @Getter private UUID clientId;

    public ServiceDeletedEvent(UUID entityId, UUID clientId) {
        super(entityId);
        this.clientId = clientId;
    }
}
