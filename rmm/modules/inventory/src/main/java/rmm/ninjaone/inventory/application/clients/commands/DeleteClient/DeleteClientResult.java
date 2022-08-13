package rmm.ninjaone.inventory.application.clients.commands.DeleteClient;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class DeleteClientResult extends BaseResult {
    private UUID id;
    private String name;
}
