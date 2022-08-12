package rmm.ninjaone.catalog.application.services.queries.ServiceDetails;

import javax.validation.Validator;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Condition;
import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class ServiceDetailsValidator extends BaseValidator<ServiceDetailsQuery, ServiceDetailsResult> {

    public ServiceDetailsValidator(Validator validator) {
        super(validator);
    }

    @Override
    public void validate(ServiceDetailsQuery command) {

        var hasId = new Condition<ServiceDetailsQuery>(m -> m.getId() != null, "Id");
        var hasEmail = new Condition<ServiceDetailsQuery>(m -> m.getSku() != null, "Sku");
        
        assertThat(command).is(anyOf(hasId, hasEmail));
    }
}