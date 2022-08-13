package rmm.ninjaone.inventory.infrastructure.endpoints.services;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse {
    private UUID id;
    private String name;
}