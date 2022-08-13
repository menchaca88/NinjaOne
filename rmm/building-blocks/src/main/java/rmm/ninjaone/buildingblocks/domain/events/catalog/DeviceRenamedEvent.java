package rmm.ninjaone.buildingblocks.domain.events.catalog;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class DeviceRenamedEvent extends Event {
    @Getter private String oldName;
    @Getter private String newName;

    public DeviceRenamedEvent(UUID entityID, String oldName, String newName) {
        super(entityID);
        this.oldName = oldName;
        this.newName = newName;
    }
}