package rmm.ninjaone.catalog.application.devices.commands.UpdateDevice;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("UpdateDeviceTypeValidator")
public class UpdateDeviceValidator extends BaseValidator<UpdateDeviceCommand, UpdateDeviceResult> {

    public UpdateDeviceValidator(Validator validator) {
        super(validator);
    }
}
