package rmm.ninjaone.catalog.application.services.queries.ListServices;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListServicesItem {
    private UUID id;
    private String name;
    private String sku;
}
