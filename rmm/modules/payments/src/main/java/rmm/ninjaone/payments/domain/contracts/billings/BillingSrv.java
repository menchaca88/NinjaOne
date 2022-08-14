package rmm.ninjaone.payments.domain.contracts.billings;

import java.util.UUID;

import rmm.ninjaone.payments.domain.models.Bill;

public interface BillingSrv {
    Bill generate(UUID payerId);
}
