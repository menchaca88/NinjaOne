package rmm.ninjaone.inventory.application.devices.commands.CreateDevice;

import javax.validation.Validator;

import static org.assertj.core.api.Assertions.*;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("CreateDeviceValidator")
public class CreateDeviceValidator extends BaseValidator<CreateDeviceCommand, CreateDeviceResult> {

    public CreateDeviceValidator(Validator validator) {
        super(validator);
    }

    @Override
    public void validate(CreateDeviceCommand command) {
        super.validate(command);

        assertThat(command.getCount()).isGreaterThan(0);
    }
}
