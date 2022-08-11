package rmm.ninjaone.identity.application.commands.DeleteUser;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.models.BaseResult;

@Getter
@Setter
public class DeleteUserResult extends BaseResult {
    private UUID id;
    private String name;
    private String email;
    private String role;
}
