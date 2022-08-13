package rmm.ninjaone.invoices.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class InvoiceNotFoundException extends DomainException {
    private final UUID id;

    public InvoiceNotFoundException(UUID id) {
        this.id = id;
    }
}
