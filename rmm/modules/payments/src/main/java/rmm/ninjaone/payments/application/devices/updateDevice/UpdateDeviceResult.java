package rmm.ninjaone.payments.application.devices.updateDevice;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class UpdateDeviceResult extends BaseResult {
    private UUID id;
    private int count;
    private String name;
}
