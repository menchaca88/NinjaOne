package rmm.ninjaone.payments.domain.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.payments.domain.contracts.payments.PaymentGateway;
import rmm.ninjaone.payments.domain.contracts.payments.PaymentSrv;
import rmm.ninjaone.payments.domain.models.Bill;

@Service
@RequiredArgsConstructor
public class PaymentSrvImpl implements PaymentSrv {
    private final PaymentGateway gateway;

    @Override
    public void pay(Bill bill) {
        bill.finish();
        
        gateway.charge(bill);
    }
}
