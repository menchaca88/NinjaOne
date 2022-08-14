package rmm.ninjaone.payments.application.devices.DeleteType;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class DeleteTypeResult extends BaseResult {
    private UUID typeId;
    private int count;
}
