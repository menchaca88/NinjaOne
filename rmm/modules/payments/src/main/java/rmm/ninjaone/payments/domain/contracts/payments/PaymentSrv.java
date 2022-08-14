package rmm.ninjaone.payments.domain.contracts.payments;

import rmm.ninjaone.payments.domain.models.Bill;

public interface PaymentSrv {
    void pay(Bill bill);
}
