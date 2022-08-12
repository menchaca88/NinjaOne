package rmm.ninjaone.catalog.application.devices.queries.DeviceDetails;

import javax.validation.Validator;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Condition;
import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class DeviceDetailsValidator extends BaseValidator<DeviceDetailsQuery, DeviceDetailsResult> {

    public DeviceDetailsValidator(Validator validator) {
        super(validator);
    }

    @Override
    public void validate(DeviceDetailsQuery command) {

        var hasId = new Condition<DeviceDetailsQuery>(m -> m.getId() != null, "Id");
        var hasEmail = new Condition<DeviceDetailsQuery>(m -> m.getSku() != null, "Sku");
        
        assertThat(command).is(anyOf(hasId, hasEmail));
    }
}