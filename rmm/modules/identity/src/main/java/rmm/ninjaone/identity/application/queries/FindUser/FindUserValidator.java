package rmm.ninjaone.identity.application.queries.FindUser;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class FindUserValidator extends BaseValidator<FindUserQuery, FindUserResult> {

    public FindUserValidator(Validator validator) {
        super(validator);
    }
}
