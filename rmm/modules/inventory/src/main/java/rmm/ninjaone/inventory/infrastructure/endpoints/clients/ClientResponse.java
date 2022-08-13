package rmm.ninjaone.inventory.infrastructure.endpoints.clients;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.inventory.infrastructure.endpoints.devices.DeviceResponse;
import rmm.ninjaone.inventory.infrastructure.endpoints.services.ServiceResponse;

@Getter
@Setter
public class ClientResponse {
    private UUID id;
    private String name;
    private List<DeviceResponse> devices;
    private List<ServiceResponse> services;
}
