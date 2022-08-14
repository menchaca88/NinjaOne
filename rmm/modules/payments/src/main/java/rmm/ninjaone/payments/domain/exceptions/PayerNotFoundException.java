package rmm.ninjaone.payments.domain.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class PayerNotFoundException extends DomainException {
    private final UUID id;

    public PayerNotFoundException(UUID id) {
        this.id = id;
    }
}
