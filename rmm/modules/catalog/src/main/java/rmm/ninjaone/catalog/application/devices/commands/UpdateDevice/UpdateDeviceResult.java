package rmm.ninjaone.catalog.application.devices.commands.UpdateDevice;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.models.BaseResult;

@Getter
@Setter
public class UpdateDeviceResult extends BaseResult {
    private UUID id;
    private String name;
    private String sku;
    private String subscription;
}
