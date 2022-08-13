package rmm.ninjaone.inventory.application.clients.queries.ClientDetails;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class ClientDetailsValidator extends BaseValidator<ClientDetailsQuery, ClientDetailsResult> {

    public ClientDetailsValidator(Validator validator) {
        super(validator);
    }
}
