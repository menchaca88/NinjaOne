package rmm.ninjaone.api.endpoints.authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotNull(message = "{errors.name.required}")
    private String fullName;
    @NotNull(message = "{errors.email.required}")
    @Email(message = "{errors.email.invalid}")
    private String email;
    @Size(min = 8, message = "{errors.password.minLength}")
    @NotNull(message = "{errors.password.required}")
    private String password;
}