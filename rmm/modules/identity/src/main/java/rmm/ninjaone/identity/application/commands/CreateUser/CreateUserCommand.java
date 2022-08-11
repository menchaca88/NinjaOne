package rmm.ninjaone.identity.application.commands.CreateUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserCommand implements Command<CreateUserResult> {
    @NotBlank private String name;
    @Email private String email;
    @NotNull private String password;
    @NotNull private String role;
}
