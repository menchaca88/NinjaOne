package rmm.ninjaone.payments.application.services.RenameType;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("RenameServiceTypeChargeValidator")
public class RenameTypeValidator extends BaseValidator<RenameTypeCommand, RenameTypeResult> {

    public RenameTypeValidator(Validator validator) {
        super(validator);
    }
}
