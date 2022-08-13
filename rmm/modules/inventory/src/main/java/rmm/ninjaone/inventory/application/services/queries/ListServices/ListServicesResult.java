package rmm.ninjaone.inventory.application.services.queries.ListServices;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class ListServicesResult extends BaseResult {
    private List<ListServicesItem> services;
}
