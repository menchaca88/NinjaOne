package rmm.ninjaone.catalog.application.devices.commands.CreateDevice;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.models.BaseResult;

@Getter
@Setter
public class CreateDeviceResult extends BaseResult {
    private UUID id;
    private String name;
    private String sku;
    private String subscription;
}
