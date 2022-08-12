package rmm.ninjaone.catalog.infrastructure.endpoints.services;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateServiceRequest {
    @NotBlank(message = "{errors.name.required}")
    private String name;
}
