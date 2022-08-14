package rmm.ninjaone.payments.application.payments.PayBill;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class PayBillValidator extends BaseValidator<PayBillCommand, PayBillResult> {

    public PayBillValidator(Validator validator) {
        super(validator);
    }
}
