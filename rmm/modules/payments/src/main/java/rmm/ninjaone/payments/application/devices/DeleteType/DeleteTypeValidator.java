package rmm.ninjaone.payments.application.devices.DeleteType;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("DeleteTypeDeviceChargeValidator")
public class DeleteTypeValidator extends BaseValidator<DeleteTypeCommand, DeleteTypeResult> {

    public DeleteTypeValidator(Validator validator) {
        super(validator);
    }
}
