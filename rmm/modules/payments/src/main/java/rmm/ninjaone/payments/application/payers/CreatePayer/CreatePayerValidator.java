package rmm.ninjaone.payments.application.payers.CreatePayer;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class CreatePayerValidator extends BaseValidator<CreatePayerCommand, CreatePayerResult> {

    public CreatePayerValidator(Validator validator) {
        super(validator);
    }
}
