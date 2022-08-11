package rmm.ninjaone.identity.infrastructure.endpoints.users;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotNull
    private UUID id;
    @NotBlank(message = "{errors.name.required}")
    private String name;
    @Email(message = "{errors.email.invalid}")
    private String email;
    private String password;
}
