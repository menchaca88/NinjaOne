package rmm.ninjaone.identity.data;

import rmm.ninjaone.buildingblocks.data.EmailMother;
import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.identity.application.commands.CreateUser.CreateUserCommand;

public class CreateUserMother extends ObjectMother {
    public static CreateUserCommand random() {
        var command = new CreateUserCommand();

        command.setName(StringMother.random());
        command.setEmail(EmailMother.random());
        command.setPassword(StringMother.random());
        command.setRole("User");

        return command;
    }
}
