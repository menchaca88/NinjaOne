package rmm.ninjaone.payments.application.payers.DeletePayer;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class DeletePayerValidator extends BaseValidator<DeletePayerCommand, DeletePayerResult> {

    public DeletePayerValidator(Validator validator) {
        super(validator);
    }
}
