package rmm.ninjaone.payments.application.devices.RenameType;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class RenameTypeResult extends BaseResult {
    private UUID typeId;
    private int count;
    private String name;
}
