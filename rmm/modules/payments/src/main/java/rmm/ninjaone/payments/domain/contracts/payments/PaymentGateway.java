package rmm.ninjaone.payments.domain.contracts.payments;

import rmm.ninjaone.payments.domain.models.Bill;

public interface PaymentGateway {
    void charge(Bill bill);
}
