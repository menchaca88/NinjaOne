package rmm.ninjaone.inventory.application.services.queries.ListServices;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component("ListServicesValidator")
public class ListServicesValidator extends BaseValidator<ListServicesQuery, ListServicesResult> {

    public ListServicesValidator(Validator validator) {
        super(validator);
    }
}
