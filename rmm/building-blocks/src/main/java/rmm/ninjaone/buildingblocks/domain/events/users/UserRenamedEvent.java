package rmm.ninjaone.buildingblocks.domain.events.users;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class UserRenamedEvent extends Event {
    @Getter private String oldName;
    @Getter private String newName;

    public UserRenamedEvent(UUID entityId, String oldName, String newName) {
        super(entityId);
        this.oldName = oldName;
        this.newName = newName;
    }
}
