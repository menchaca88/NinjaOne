package rmm.ninjaone.inventory.application.services.commands.DeleteType;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("DeleteTypeServiceValidator")
public class DeleteTypeValidator extends BaseValidator<DeleteTypeCommand, DeleteTypeResult> {

    public DeleteTypeValidator(Validator validator) {
        super(validator);
    }
}
