package rmm.ninjaone.inventory.application.devices.commands.DeleteDevice;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("DeleteDeviceValidator")
public class DeleteDeviceValidator extends BaseValidator<DeleteDeviceCommand, DeleteDeviceResult> {

    public DeleteDeviceValidator(Validator validator) {
        super(validator);
    }
}
