package rmm.ninjaone.payments.application.payers.UpdatePayer;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class UpdatePayerValidator extends BaseValidator<UpdatePayerCommand, UpdatePayerResult> {

    public UpdatePayerValidator(Validator validator) {
        super(validator);
    }
}
