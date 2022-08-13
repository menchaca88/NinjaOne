package rmm.ninjaone.inventory.infrastructure.endpoints.devices;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceResponse {
    private UUID id;
    private int count;
    private String name;
}