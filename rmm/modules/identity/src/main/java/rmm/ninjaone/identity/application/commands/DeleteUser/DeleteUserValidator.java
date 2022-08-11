package rmm.ninjaone.identity.application.commands.DeleteUser;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class DeleteUserValidator extends BaseValidator<DeleteUserCommand, DeleteUserResult> {

    public DeleteUserValidator(Validator validator) {
        super(validator);
    }
}
