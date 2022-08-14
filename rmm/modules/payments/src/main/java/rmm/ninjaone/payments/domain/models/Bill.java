package rmm.ninjaone.payments.domain.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Value;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;
import rmm.ninjaone.buildingblocks.domain.events.payments.BillCreatedEvent;
import rmm.ninjaone.payments.domain.exceptions.BillAlreadyPaidException;

public class Bill extends AggregateRoot {
    @Getter private final UUID payerId;
    @Getter private final String payerName;
    @Getter private Date date;
    private List<BillingDevice> devices;
    private List<BillingService> services;

    public Bill(UUID payerId, String payerName) {
        super();
        this.payerId = payerId;
        this.payerName = payerName;
        devices = new ArrayList<>();
        services = new ArrayList<>();
    }

    public double getTotal() {
        var devicesTotal = devices
            .stream()
            .map(x -> x.getToCharge())
            .reduce(0.0, Double::sum);

        var servicesTotal = services
            .stream()
            .map(x -> x.getToCharge())
            .reduce(0.0, Double::sum);

        return devicesTotal + servicesTotal;
    }

    public void finish() {
        if (date != null)
            throw new BillAlreadyPaidException(getId());

        date = new Date();
        registerFinishEvent();
    }

    private void registerFinishEvent() {
        var chargedDevices = devices
            .stream()
            .map(d -> new BillCreatedEvent
                .BilledDevice(d.getName(), d.getCount(), d.getToCharge()))
            .toList();

        var chargedServices = services
            .stream()
            .map(s -> new BillCreatedEvent
                .BilledService(s.getName(), s.getToCharge()))
            .toList();

        var event = new BillCreatedEvent(getId(), getPayerId(), getPayerName(), getDate(), chargedDevices, chargedServices);
        registerEvent(event);
    }

    public void addDeviceItem(String name, int count, double toCharge) {
        var item = new BillingDevice(name, count, toCharge);
        devices.add(item);
    }

    public void addServiceItem(String name, double toCharge) {
        var item = new BillingService(name, toCharge);
        services.add(item);
    }

    @Value
    public static class BillingService {
        private final String name;
        private final double toCharge;
    }

    @Value
    public static class BillingDevice {
        private final String name;
        private final int count;
        private final double toCharge;
    }
}
