package rmm.ninjaone.inventory.domain.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class DeviceTypeNotFoundException extends DomainException {
    private final UUID id;

    public DeviceTypeNotFoundException(UUID id) {
        this.id = id;
    }
}
