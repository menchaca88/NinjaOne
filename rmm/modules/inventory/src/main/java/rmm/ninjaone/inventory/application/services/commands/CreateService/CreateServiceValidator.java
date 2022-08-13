package rmm.ninjaone.inventory.application.services.commands.CreateService;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("CreateServiceValidator")
public class CreateServiceValidator extends BaseValidator<CreateServiceCommand, CreateServiceResult> {

    public CreateServiceValidator(Validator validator) {
        super(validator);
    }
}
