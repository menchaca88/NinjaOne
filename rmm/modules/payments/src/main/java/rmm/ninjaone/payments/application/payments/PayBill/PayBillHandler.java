package rmm.ninjaone.payments.application.payments.PayBill;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.billings.BillingSrv;
import rmm.ninjaone.payments.domain.contracts.payments.PaymentSrv;

@Component
public class PayBillHandler extends BaseHandler<PayBillCommand, PayBillResult> {
    private BillingSrv billingSrv;
    private PaymentSrv paymentSrv;

    public PayBillHandler(UserContext context, BillingSrv billingSrv, PaymentSrv paymentSrv) {
        super(context);
        this.billingSrv = billingSrv;
        this.paymentSrv = paymentSrv;
    }
    @Override
    public PayBillResult handle(PayBillCommand command) {
        var bill = billingSrv.generate(context.getUserId());
        paymentSrv.pay(bill);

        var result = new PayBillResult();
        result.setInvoiceId(bill.getId());
        result.setTotal(bill.getTotal());

        return result;
    }
}
