package rmm.ninjaone.identity.data;

import rmm.ninjaone.buildingblocks.data.EmailMother;
import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.identity.application.queries.FindUser.FindUserQuery;

public class FindUserMother extends ObjectMother {
    public static FindUserQuery random() {
        var query = new FindUserQuery();

        query.setEmail(EmailMother.random());

        return query;
    }
}
