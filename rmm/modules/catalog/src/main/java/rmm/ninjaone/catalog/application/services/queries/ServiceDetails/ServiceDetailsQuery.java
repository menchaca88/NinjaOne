package rmm.ninjaone.catalog.application.services.queries.ServiceDetails;

import java.util.UUID;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDetailsQuery implements Command<ServiceDetailsResult> {
    private UUID id;
    private String sku;
}
