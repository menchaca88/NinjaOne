package rmm.ninjaone.inventory.application.devices.queries.ListDevices;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("ListDevicesValidator")
public class ListDevicesValidator extends BaseValidator<ListDevicesQuery, ListDevicesResult> {

    public ListDevicesValidator(Validator validator) {
        super(validator);
    }
}
