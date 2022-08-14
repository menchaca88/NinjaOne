package rmm.ninjaone.payments.application.devices.DeleteDevice;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("DeleteDeviceChargeValidator")
public class DeleteDeviceValidator extends BaseValidator<DeleteDeviceCommand, DeleteDeviceResult> {

    public DeleteDeviceValidator(Validator validator) {
        super(validator);
    }
}
