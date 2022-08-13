package rmm.ninjaone.inventory.infrastructure.endpoints.devices;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDevicesResponse {
    private List<DeviceResponse> devices;
}
