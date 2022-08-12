package rmm.ninjaone.catalog.infrastructure.endpoints.services;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListServicesResponse {
    private List<ServiceResponse> services;
}