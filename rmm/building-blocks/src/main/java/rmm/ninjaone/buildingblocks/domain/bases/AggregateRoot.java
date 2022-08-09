package rmm.ninjaone.buildingblocks.domain.bases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class AggregateRoot {
    @Getter @Setter private UUID id;
    private List<Event> domainEvents;

    protected AggregateRoot() {
        domainEvents = new ArrayList<>();
        id = UUID.randomUUID();
    }

    protected void AddEvent(Event event) {
        domainEvents.add(event);
    }

    public List<Event> dispatchEvents() {
        var dispatchedEvents = Collections.unmodifiableList(domainEvents);
        domainEvents = new ArrayList<>();

        return dispatchedEvents;
    }

    public void setDeleted() {
    }
}
