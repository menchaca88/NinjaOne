package rmm.ninjaone.inventory.application.services.commands.DeleteService;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("DeleteServiceValidator")
public class DeleteServiceValidator extends BaseValidator<DeleteServiceCommand, DeleteServiceResult> {

    public DeleteServiceValidator(Validator validator) {
        super(validator);
    }
}
