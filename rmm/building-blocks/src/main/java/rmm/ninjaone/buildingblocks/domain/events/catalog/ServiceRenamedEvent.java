package rmm.ninjaone.buildingblocks.domain.events.catalog;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class ServiceRenamedEvent extends Event {
    @Getter private String oldName;
    @Getter private String newName;

    public ServiceRenamedEvent(UUID entityID, String oldName, String newName) {
        super(entityID);
        this.oldName = oldName;
        this.newName = newName;
    }
}