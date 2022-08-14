package rmm.ninjaone.payments.application.services.DeleteType;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("DeleteTypeServiceChargeValidator")
public class DeleteTypeValidator extends BaseValidator<DeleteTypeCommand, DeleteTypeResult> {

    public DeleteTypeValidator(Validator validator) {
        super(validator);
    }
}
