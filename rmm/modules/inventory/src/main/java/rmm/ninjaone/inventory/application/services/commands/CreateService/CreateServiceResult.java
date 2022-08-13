package rmm.ninjaone.inventory.application.services.commands.CreateService;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class CreateServiceResult extends BaseResult {
    private UUID id;
    private String name;
}
