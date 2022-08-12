package rmm.ninjaone.buildingblocks.domain.events.catalog;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class ServiceDeletedEvent extends Event {

    public ServiceDeletedEvent(UUID entityID) {
        super(entityID);
    }
}