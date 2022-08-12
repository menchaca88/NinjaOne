package rmm.ninjaone.catalog.application.devices.queries.DeviceDetails;

import java.util.UUID;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceDetailsQuery implements Command<DeviceDetailsResult> {
    private UUID id;
    private String sku;
}
