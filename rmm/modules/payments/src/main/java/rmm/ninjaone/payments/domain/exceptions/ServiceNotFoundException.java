package rmm.ninjaone.payments.domain.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class ServiceNotFoundException extends DomainException {
    private final UUID id;

    public ServiceNotFoundException(UUID id) {
        this.id = id;
    }
}
