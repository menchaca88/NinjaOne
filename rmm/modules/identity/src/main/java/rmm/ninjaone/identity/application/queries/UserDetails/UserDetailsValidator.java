package rmm.ninjaone.identity.application.queries.UserDetails;

import javax.validation.Validator;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Condition;
import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseValidator;

@Component
public class UserDetailsValidator extends BaseValidator<UserDetailsQuery, UserDetailsResult> {

    public UserDetailsValidator(Validator validator) {
        super(validator);
    }

    @Override
    public void validate(UserDetailsQuery command) {

        var hasId = new Condition<UserDetailsQuery>(m -> m.getId() != null, "Id");
        var hasEmail = new Condition<UserDetailsQuery>(m -> m.getEmail() != null, "Email");
        
        assertThat(command).is(anyOf(hasId, hasEmail));
    }
}