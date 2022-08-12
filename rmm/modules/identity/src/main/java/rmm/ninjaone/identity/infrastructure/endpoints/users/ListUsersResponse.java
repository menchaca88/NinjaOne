package rmm.ninjaone.identity.infrastructure.endpoints.users;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListUsersResponse {
    private List<UserResponse> users;
}