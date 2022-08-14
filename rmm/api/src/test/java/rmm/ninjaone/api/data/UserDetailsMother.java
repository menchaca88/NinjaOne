package rmm.ninjaone.api.data;

import java.util.UUID;

import rmm.ninjaone.api.security.Authorities;
import rmm.ninjaone.buildingblocks.data.EmailMother;
import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.identity.application.queries.UserDetails.UserDetailsQuery;
import rmm.ninjaone.identity.application.queries.UserDetails.UserDetailsResult;

public class UserDetailsMother extends ObjectMother {
    public static UserDetailsQuery queryId() {
        var query = new UserDetailsQuery();

        query.setId(UUID.randomUUID());

        return query;
    }

    public static UserDetailsQuery queryEmail() {
        var query = new UserDetailsQuery();

        query.setEmail(EmailMother.random());

        return query;
    }

    public static UserDetailsResult withId(UUID id) {
        var result = new UserDetailsResult();

        result.setId(id);
        result.setName(StringMother.random());
        result.setEmail(EmailMother.random());
        result.setPassword(StringMother.random());
        result.setRole(Authorities.User);

        return result;
    }

    public static UserDetailsResult withEmail(String email) {
        var result = new UserDetailsResult();

        result.setId(UUID.randomUUID());
        result.setName(StringMother.random());
        result.setEmail(email);
        result.setPassword(StringMother.random());
        result.setRole(Authorities.User);

        return result;
    }
}
