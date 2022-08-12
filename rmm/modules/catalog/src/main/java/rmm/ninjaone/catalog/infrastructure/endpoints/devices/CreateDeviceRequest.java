package rmm.ninjaone.catalog.infrastructure.endpoints.devices;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateDeviceRequest {
    @NotBlank(message = "{errors.name.required}")
    private String name;
}
