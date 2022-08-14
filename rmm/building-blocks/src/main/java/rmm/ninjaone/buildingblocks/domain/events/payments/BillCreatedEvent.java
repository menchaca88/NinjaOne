package rmm.ninjaone.buildingblocks.domain.events.payments;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Value;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

@Getter
public class BillCreatedEvent extends Event {
    private UUID payerId;
    private String payerName;
    private Date date;
    private List<BilledDevice> devices;
    private List<BilledService> services;

    public BillCreatedEvent(UUID entityId, UUID payerId, String payerName, Date date, List<BilledDevice> devices, List<BilledService> services) {
        super(entityId);
        this.payerId = payerId;
        this.payerName = payerName;
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
