package rmm.ninjaone.inventory.application.clients.commands.CreateClient;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class CreateClientValidator extends BaseValidator<CreateClientCommand, CreateClientResult> {

    public CreateClientValidator(Validator validator) {
        super(validator);
    }
}
