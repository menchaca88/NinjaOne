package rmm.ninjaone.api.data;

import rmm.ninjaone.api.endpoints.authentication.LoginUserRequest;
import rmm.ninjaone.buildingblocks.data.EmailMother;
import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;

public class LoginUserMother extends ObjectMother {
    public static LoginUserRequest valid() {
        var request = new LoginUserRequest();

        request.setEmail(EmailMother.random());
        request.setPassword(StringMother.random(8, 16));

        return request;
    }
}
