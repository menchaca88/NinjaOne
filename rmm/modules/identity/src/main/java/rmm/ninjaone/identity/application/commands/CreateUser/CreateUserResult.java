package rmm.ninjaone.identity.application.commands.CreateUser;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class CreateUserResult extends BaseResult {
    private UUID id;
    private String name;
    private String email;
    private String role;
}
