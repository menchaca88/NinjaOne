package rmm.ninjaone.catalog.application.services.commands.UpdateService;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class UpdateServiceValidator extends BaseValidator<UpdateServiceCommand, UpdateServiceResult> {

    public UpdateServiceValidator(Validator validator) {
        super(validator);
    }
}
