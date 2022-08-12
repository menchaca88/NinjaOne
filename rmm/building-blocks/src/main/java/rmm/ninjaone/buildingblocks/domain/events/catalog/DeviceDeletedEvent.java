package rmm.ninjaone.buildingblocks.domain.events.catalog;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class DeviceDeletedEvent extends Event {

    public DeviceDeletedEvent(UUID entityID) {
        super(entityID);
    }
}