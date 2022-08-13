package rmm.ninjaone.inventory.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Client extends AggregateRoot {
    @OneToMany 
    @JoinColumn(name = "clientId")
    private List<Device> devices;
    @OneToMany
    @JoinColumn(name = "clientId")
    private List<Service> services;
    private String name;

    private Client(UUID id, String name) {
        super(id);
        this.name = name;
        devices = new ArrayList<>();
        services = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void rename(@NonNull String name) {
        this.name = name;
    }

    public void addDevice(@NonNull Device device) {
        devices.add(device);
    }

    public void addService(@NonNull Service service) {
        services.add(service);
    }

    public List<Device> getDevices() {
        return Collections.unmodifiableList(devices);
    }

    public List<Service> getServices() {
        return Collections.unmodifiableList(services);
    }

    public static Client create(@NonNull UUID id, @NonNull String name) {
        return new Client(id, name);
    }
}
