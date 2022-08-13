package rmm.ninjaone.buildingblocks.domain.events.payments;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Value;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

public class BillCreatedEvent extends Event {
    @Getter private UUID clientId;
    @Getter private Date date;
    @Getter private List<BilledDevice> devices;
    @Getter private List<BilledService> services;

    public BillCreatedEvent(UUID entityId, UUID clientId, Date date, List<BilledDevice> devices, List<BilledService> services) {
        super(entityId);
        this.clientId = clientId;
        this.date = date;
        this.devices = devices;
        this.services = services;
    }

    @Value
    public static class BilledService {
        private final String name;
        private final double charged;
    }

    @Value
    public static class BilledDevice {
        private final String name;
        private final int count;
        private final double charged;
    }
}
