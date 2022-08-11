package rmm.ninjaone.identity.application.queries.UserDetails;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.models.BaseResult;

@Getter
@Setter
public class UserDetailsResult extends BaseResult {
    private UUID id;
    private String name;
    private String email;
    private String role;
    private String password;
}
