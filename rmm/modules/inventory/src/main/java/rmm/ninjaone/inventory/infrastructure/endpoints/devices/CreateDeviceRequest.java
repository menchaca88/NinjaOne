package rmm.ninjaone.inventory.infrastructure.endpoints.devices;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateDeviceRequest {
    private int count;
    @NotNull(message = "{errors.devices.required}")
    private UUID typeId;
}
