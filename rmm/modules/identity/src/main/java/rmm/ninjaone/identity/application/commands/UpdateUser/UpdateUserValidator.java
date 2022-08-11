package rmm.ninjaone.identity.application.commands.UpdateUser;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class UpdateUserValidator extends BaseValidator<UpdateUserCommand, UpdateUserResult> {

    public UpdateUserValidator(Validator validator) {
        super(validator);
    }
}
