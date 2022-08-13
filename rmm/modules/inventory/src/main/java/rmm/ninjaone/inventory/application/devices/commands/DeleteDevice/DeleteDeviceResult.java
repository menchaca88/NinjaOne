package rmm.ninjaone.inventory.application.devices.commands.DeleteDevice;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class DeleteDeviceResult extends BaseResult {
    private UUID id;
    private int count;
    private String name;
}
