package rmm.ninjaone.catalog.application.devices.queries.ListDevices;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDevicesItem {
    private UUID id;
    private String name;
    private String sku;
}
