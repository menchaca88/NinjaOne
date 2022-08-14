package rmm.ninjaone.payments.application.devices.updateDevice;

import javax.validation.Validator;

import static org.assertj.core.api.Assertions.*;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("UpdateDeviceChargeValidator")
public class UpdateDeviceValidator extends BaseValidator<UpdateDeviceCommand, UpdateDeviceResult> {

    public UpdateDeviceValidator(Validator validator) {
        super(validator);
    }

    @Override
    public void validate(UpdateDeviceCommand command) {
        super.validate(command);

        assertThat(command.getCount()).isGreaterThan(0);
    }
}
