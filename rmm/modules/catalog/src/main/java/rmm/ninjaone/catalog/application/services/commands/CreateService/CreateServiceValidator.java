package rmm.ninjaone.catalog.application.services.commands.CreateService;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("CreateServiceTypeValidator")
public class CreateServiceValidator extends BaseValidator<CreateServiceCommand, CreateServiceResult> {

    public CreateServiceValidator(Validator validator) {
        super(validator);
    }
}
