package rmm.ninjaone.payments.application.payers.CreatePayer;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class CreatePayerResult extends BaseResult {
    private UUID id;
    private String name;
}
