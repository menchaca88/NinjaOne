package rmm.ninjaone.catalog.domain.exceptions;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class DeviceAlreadyExistsException extends DomainException {
    private final String name;
    
    public DeviceAlreadyExistsException(String name) {
        this.name = name;
    }
}
