package rmm.ninjaone.buildingblocks.domain.events.users;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class UserAddedEvent extends Event {
    @Getter private String name;

    public UserAddedEvent(UUID entityId, String name) {
        super(entityId);
        this.name = name;
    }
}
