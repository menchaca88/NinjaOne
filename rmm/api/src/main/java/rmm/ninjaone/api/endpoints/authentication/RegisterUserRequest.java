package rmm.ninjaone.api.endpoints.authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotNull(message = "{errors.name.required}")
    private String fullName;
    @NotNull(message = "{errors.email.required}")
    @Email(message = "{errors.email.invalid}")
    private String email;
    @NotNull(message = "{errors.password.required}")
    private String password;
}