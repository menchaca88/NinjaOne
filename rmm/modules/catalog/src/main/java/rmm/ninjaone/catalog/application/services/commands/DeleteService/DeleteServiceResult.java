package rmm.ninjaone.catalog.application.services.commands.DeleteService;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class DeleteServiceResult extends BaseResult {
    private UUID id;
    private String name;
    private String sku;
    private String subscription;
}
