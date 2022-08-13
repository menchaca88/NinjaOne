package rmm.ninjaone.inventory.application.services.commands.RenameType;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("RenameServiceTypeValidator")
public class RenameTypeValidator extends BaseValidator<RenameTypeCommand, RenameTypeResult> {

    public RenameTypeValidator(Validator validator) {
        super(validator);
    }
}
