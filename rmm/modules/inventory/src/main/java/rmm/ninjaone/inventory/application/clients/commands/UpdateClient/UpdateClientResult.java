package rmm.ninjaone.inventory.application.clients.commands.UpdateClient;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class UpdateClientResult extends BaseResult {
    private UUID id;
    private String name;
}
