package rmm.ninjaone.identity.application.queries.ListUsers;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListUsersItem {
    private UUID id;
    private String name;
    private String email;
    private String role;
}
