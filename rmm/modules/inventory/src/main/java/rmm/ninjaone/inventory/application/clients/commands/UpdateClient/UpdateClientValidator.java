package rmm.ninjaone.inventory.application.clients.commands.UpdateClient;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class UpdateClientValidator extends BaseValidator<UpdateClientCommand, UpdateClientResult> {

    public UpdateClientValidator(Validator validator) {
        super(validator);
    }
}
