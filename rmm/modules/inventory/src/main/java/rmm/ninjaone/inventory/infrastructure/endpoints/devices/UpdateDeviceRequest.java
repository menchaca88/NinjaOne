package rmm.ninjaone.inventory.infrastructure.endpoints.devices;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateDeviceRequest {
    @NotNull(message = "{errors.identifier.required}")
    private UUID id;
    private int count;
}
