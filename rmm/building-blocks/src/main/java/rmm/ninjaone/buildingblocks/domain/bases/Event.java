package rmm.ninjaone.buildingblocks.domain.bases;

import java.util.Date;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PRIVATE)
public abstract class Event {
    private UUID entityId;
    private UUID uuid;
    private Date occurredOn;

    public Event(UUID entityID) {
        setEntityId(entityId);
        setUuid(UUID.randomUUID());
        setOccurredOn(new Date());
    }
}
