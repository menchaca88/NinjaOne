package rmm.ninjaone.inventory.application.clients.commands.DeleteClient;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class DeleteClientValidator extends BaseValidator<DeleteClientCommand, DeleteClientResult> {

    public DeleteClientValidator(Validator validator) {
        super(validator);
    }
}
