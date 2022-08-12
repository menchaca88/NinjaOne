package rmm.ninjaone.catalog.port;

import java.util.UUID;

import org.springframework.stereotype.Service;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.ports.catalog.CatalogPort;
import rmm.ninjaone.catalog.application.devices.queries.DeviceDetails.DeviceDetailsQuery;
import rmm.ninjaone.catalog.application.services.queries.ServiceDetails.ServiceDetailsQuery;
import rmm.ninjaone.catalog.domain.exceptions.DeviceNotFoundException;

@Service
@RequiredArgsConstructor
public class CatalogPortImpl implements CatalogPort {
    private final Pipeline pipeline;

    @Override
    public boolean existsDevice(UUID id) {
        var query = new DeviceDetailsQuery();
        query.setId(id);

        try {
            var result = pipeline.send(query);
            return result != null;
        }
        catch (DeviceNotFoundException ex) {
            return false;
        }
    }

    @Override
    public boolean existsService(UUID id) {
        var query = new ServiceDetailsQuery();
        query.setId(id);

        try {
            var result = pipeline.send(query);
            return result != null;
        }
        catch (DeviceNotFoundException ex) {
            return false;
        }
    }
}
