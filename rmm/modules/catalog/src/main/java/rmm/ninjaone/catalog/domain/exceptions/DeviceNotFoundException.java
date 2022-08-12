package rmm.ninjaone.catalog.domain.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class DeviceNotFoundException extends DomainException {
    private final String sku;
    private final UUID id;

    public DeviceNotFoundException(String sku) {
        this.sku = sku;
        this.id = null;
    }

    public DeviceNotFoundException(UUID id) {
        this.sku = null;
        this.id = id;
    }
}
