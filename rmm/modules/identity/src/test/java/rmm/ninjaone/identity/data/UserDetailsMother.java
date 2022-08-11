package rmm.ninjaone.identity.data;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.EmailMother;
import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.identity.application.queries.UserDetails.UserDetailsQuery;

public class UserDetailsMother extends ObjectMother {
    public static UserDetailsQuery byId() {
        var query = new UserDetailsQuery();

        query.setId(UUID.randomUUID());

        return query;
    }

    public static UserDetailsQuery byEmail() {
        var query = new UserDetailsQuery();

        query.setEmail(EmailMother.random());

        return query;
    }

    public static UserDetailsQuery invalid() {
        return new UserDetailsQuery();
    }
}
