package rmm.ninjaone.catalog.infrastructure.endpoints.services;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateServicesRequest {
    @NotNull(message = "{errors.identifier.required}")
    private UUID id;
    @NotBlank(message = "{errors.name.required}")
    private String name;
}
