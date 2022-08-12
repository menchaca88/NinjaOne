package rmm.ninjaone.catalog.application.subscriptions.queries.ListSubscriptionTypes;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class ListSubscriptionTypesResult extends BaseResult {
    private List<String> devices;
    private List<String> services;
}