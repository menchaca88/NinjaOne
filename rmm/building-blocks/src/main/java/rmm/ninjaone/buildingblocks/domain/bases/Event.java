package rmm.ninjaone.buildingblocks.domain.bases;

import java.util.Date;
import java.util.UUID;

import an.awesome.pipelinr.Notification;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PRIVATE)
public abstract class Event implements Notification {
    private UUID entityId;
    private UUID uuid;
    private Date occurredOn;

    public Event(UUID entityId) {
        setEntityId(entityId);
        setUuid(UUID.randomUUID());
        setOccurredOn(new Date());
    }
}
