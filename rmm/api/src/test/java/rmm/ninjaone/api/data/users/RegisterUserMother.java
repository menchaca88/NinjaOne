package rmm.ninjaone.api.data.users;

import rmm.ninjaone.api.endpoints.authentication.RegisterUserRequest;
import rmm.ninjaone.buildingblocks.data.EmailMother;
import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;

public class RegisterUserMother extends ObjectMother {
    public static RegisterUserRequest valid() {
        var request = new RegisterUserRequest();

        request.setEmail(EmailMother.random());
        request.setName(StringMother.random());
        request.setPassword(StringMother.random(8, 16));

        return request;
    }
}
