package rmm.ninjaone.catalog.application.devices.commands.CreateDevice;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class CreateDeviceValidator extends BaseValidator<CreateDeviceCommand, CreateDeviceResult> {

    public CreateDeviceValidator(Validator validator) {
        super(validator);
    }
}
