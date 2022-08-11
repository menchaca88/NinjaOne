package rmm.ninjaone.identity.application.commands.CreateUser;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class CreateUserValidator extends BaseValidator<CreateUserCommand, CreateUserResult> {

    public CreateUserValidator(Validator validator) {
        super(validator);
    }
}
