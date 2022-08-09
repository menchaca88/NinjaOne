package rmm.ninjaone.buildingblocks.domain.events.users;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class UserDeletedEvent extends Event {

    public UserDeletedEvent(UUID entityID) {
        super(entityID);
    }
}