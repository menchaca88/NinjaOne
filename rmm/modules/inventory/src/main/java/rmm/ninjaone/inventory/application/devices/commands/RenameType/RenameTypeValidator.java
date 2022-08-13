package rmm.ninjaone.inventory.application.devices.commands.RenameType;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("RenameDeviceTypeValidator")
public class RenameTypeValidator extends BaseValidator<RenameTypeCommand, RenameTypeResult> {

    public RenameTypeValidator(Validator validator) {
        super(validator);
    }
}
