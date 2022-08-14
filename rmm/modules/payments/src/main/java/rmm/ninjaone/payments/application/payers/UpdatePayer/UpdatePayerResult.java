package rmm.ninjaone.payments.application.payers.UpdatePayer;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class UpdatePayerResult extends BaseResult {
    private UUID id;
    private String name;
}
