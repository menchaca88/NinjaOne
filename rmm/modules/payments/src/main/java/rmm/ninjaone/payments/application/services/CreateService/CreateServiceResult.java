package rmm.ninjaone.payments.application.services.CreateService;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class CreateServiceResult extends BaseResult {
    private UUID id;
    private String name;
}
