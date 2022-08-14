package rmm.ninjaone.payments.application.services.CreateService;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("CreateServiceChargeValidator")
public class CreateServiceValidator extends BaseValidator<CreateServiceCommand, CreateServiceResult> {

    public CreateServiceValidator(Validator validator) {
        super(validator);
    }
}
