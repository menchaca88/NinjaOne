package rmm.ninjaone.catalog.application.devices.commands.DeleteDevice;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("DeleteDeviceTypeValidator")
public class DeleteDeviceValidator extends BaseValidator<DeleteDeviceCommand, DeleteDeviceResult> {

    public DeleteDeviceValidator(Validator validator) {
        super(validator);
    }
}
