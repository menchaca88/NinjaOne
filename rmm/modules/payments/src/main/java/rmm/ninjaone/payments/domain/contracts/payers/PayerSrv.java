package rmm.ninjaone.payments.domain.contracts.payers;

import java.util.UUID;

import rmm.ninjaone.payments.domain.models.Payer;

public interface PayerSrv {
    Payer create(UUID id, String name);
    Payer update(UUID id, String name);
    Payer delete(UUID id);
}
