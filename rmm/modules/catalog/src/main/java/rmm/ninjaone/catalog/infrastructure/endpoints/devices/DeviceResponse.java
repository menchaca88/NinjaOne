package rmm.ninjaone.catalog.infrastructure.endpoints.devices;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceResponse {
    private UUID id;
    private String name;
    private String sku;
    private String subscription;
}
