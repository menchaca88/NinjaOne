package rmm.ninjaone.catalog.domain.exceptions;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class ServiceAlreadyExistsException extends DomainException {
    private final String name;
    
    public ServiceAlreadyExistsException(String name) {
        this.name = name;
    }
}
