package rmm.ninjaone.catalog.application.services.commands.UpdateService;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.models.BaseResult;

@Getter
@Setter
public class UpdateServiceResult extends BaseResult {
    private UUID id;
    private String name;
    private String sku;
}
