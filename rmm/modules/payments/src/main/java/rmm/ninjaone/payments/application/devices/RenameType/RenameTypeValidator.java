package rmm.ninjaone.payments.application.devices.RenameType;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("RenameDeviceTypeChargeValidator")
public class RenameTypeValidator extends BaseValidator<RenameTypeCommand, RenameTypeResult> {

    public RenameTypeValidator(Validator validator) {
        super(validator);
    }
}
