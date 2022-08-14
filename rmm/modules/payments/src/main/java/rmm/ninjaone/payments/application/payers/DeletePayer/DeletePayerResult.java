package rmm.ninjaone.payments.application.payers.DeletePayer;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class DeletePayerResult extends BaseResult {
    private UUID id;
    private String name;
}
