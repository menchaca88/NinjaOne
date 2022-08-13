package rmm.ninjaone.catalog.port;

import java.util.UUID;

import org.springframework.stereotype.Service;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.ports.catalog.CatalogPort;
import rmm.ninjaone.buildingblocks.ports.catalog.DeviceDetails;
import rmm.ninjaone.buildingblocks.ports.catalog.ServiceDetails;
import rmm.ninjaone.catalog.application.devices.queries.DeviceDetails.DeviceDetailsQuery;
import rmm.ninjaone.catalog.application.services.queries.ServiceDetails.ServiceDetailsQuery;
import rmm.ninjaone.catalog.domain.exceptions.DeviceNotFoundException;

@Service
@RequiredArgsConstructor
public class CatalogPortImpl implements CatalogPort {
    private final Pipeline pipeline;

    @Override
    public DeviceDetails findDevice(UUID id) {
        var query = new DeviceDetailsQuery();
        query.setId(id);

        try {
            var result = pipeline.send(query);
            return new DeviceDetails(result.getId(), result.getName(), result.getSku());
        }
        catch (DeviceNotFoundException ex) {
            return null;
        }
    }

    @Override
    public ServiceDetails findService(UUID id) {
        var query = new ServiceDetailsQuery();
        query.setId(id);

        try {
            var result = pipeline.send(query);
            return new ServiceDetails(result.getId(), result.getName(), result.getSku());
        }
        catch (DeviceNotFoundException ex) {
            return null;
        }
    }
}
