package rmm.ninjaone.payments.application.devices.CreateDevice;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class CreateDeviceResult extends BaseResult {
    private UUID id;
    private int count;
    private String name;
}
