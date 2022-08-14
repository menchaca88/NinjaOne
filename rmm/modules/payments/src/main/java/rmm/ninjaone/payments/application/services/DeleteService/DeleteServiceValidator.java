package rmm.ninjaone.payments.application.services.DeleteService;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("DeleteServiceChargeValidator")
public class DeleteServiceValidator extends BaseValidator<DeleteServiceCommand, DeleteServiceResult> {

    public DeleteServiceValidator(Validator validator) {
        super(validator);
    }
}
