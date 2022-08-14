package rmm.ninjaone.payments.application.payments.PayBill;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class PayBillResult extends BaseResult {
    private UUID invoiceId;
    private double total;
}
