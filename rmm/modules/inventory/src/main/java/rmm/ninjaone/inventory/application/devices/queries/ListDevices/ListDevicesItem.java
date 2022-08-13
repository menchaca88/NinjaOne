package rmm.ninjaone.inventory.application.devices.queries.ListDevices;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDevicesItem {
    private UUID id;
    private int count;
    private String name;
}
