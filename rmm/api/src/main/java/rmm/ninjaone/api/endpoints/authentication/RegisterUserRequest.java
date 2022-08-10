package rmm.ninjaone.api.endpoints.authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "{errors.name.required}")
    private String name;
    @Email(message = "{errors.email.invalid}")
    private String email;
    @NotNull(message = "{errors.password.required}")
    private String password;
}