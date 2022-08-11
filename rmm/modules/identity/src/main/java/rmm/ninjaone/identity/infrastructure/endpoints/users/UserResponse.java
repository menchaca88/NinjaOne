package rmm.ninjaone.identity.infrastructure.endpoints.users;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String role;
}
