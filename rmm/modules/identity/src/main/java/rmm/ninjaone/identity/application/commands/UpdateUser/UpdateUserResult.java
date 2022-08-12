package rmm.ninjaone.identity.application.commands.UpdateUser;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class UpdateUserResult extends BaseResult {
    private UUID id;
    private String name;
    private String email;
    private String role;
}
