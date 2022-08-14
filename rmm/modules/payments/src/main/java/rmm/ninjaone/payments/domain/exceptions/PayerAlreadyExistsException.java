package rmm.ninjaone.payments.domain.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class PayerAlreadyExistsException extends DomainException {
    private final UUID id;
    
    public PayerAlreadyExistsException(UUID id) {
        this.id = id;
    }
}
