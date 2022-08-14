package rmm.ninjaone.payments.domain.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class BillAlreadyPaidException extends DomainException {
    private final UUID billId;

    public BillAlreadyPaidException(UUID id) {
        this.billId = id;
    }
}
