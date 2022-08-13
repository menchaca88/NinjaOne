package rmm.ninjaone.inventory.infrastructure.endpoints.services;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateServiceRequest {
    @NotNull(message = "{errors.services.required}")
    private UUID typeId;
}
