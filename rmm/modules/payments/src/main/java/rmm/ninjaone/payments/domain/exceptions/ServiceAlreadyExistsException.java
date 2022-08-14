package rmm.ninjaone.payments.domain.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class ServiceAlreadyExistsException extends DomainException {
    private final UUID id;
    
    public ServiceAlreadyExistsException(UUID id) {
        this.id = id;
    }
}
