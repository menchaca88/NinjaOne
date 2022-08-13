package rmm.ninjaone.inventory.application.clients.queries.ClientDetails;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class ClientDetailsResult extends BaseResult {
    private UUID id;
    private String name;
    private List<ClientDetailsDevice> devices;
    private List<ClientDetailsService> services;
}
