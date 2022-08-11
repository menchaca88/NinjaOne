package rmm.ninjaone.identity.application.commands.UpdateUser;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserCommand implements Command<UpdateUserResult> {
    @NotNull private UUID id;
    @NotBlank private String name;
    @Email private String email;
    private String password;
}
