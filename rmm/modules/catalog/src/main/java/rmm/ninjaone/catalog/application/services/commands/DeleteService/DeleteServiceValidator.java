package rmm.ninjaone.catalog.application.services.commands.DeleteService;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class DeleteServiceValidator extends BaseValidator<DeleteServiceCommand, DeleteServiceResult> {

    public DeleteServiceValidator(Validator validator) {
        super(validator);
    }
}
