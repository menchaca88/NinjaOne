package rmm.ninjaone.inventory.application.clients.queries.ClientDetails;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDetailsDevice {
    private UUID id;
    private int count;
    private String name;
}
