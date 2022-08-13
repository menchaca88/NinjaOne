package rmm.ninjaone.inventory.application.clients.queries.ClientDetails;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientSrv;

@Component
public class ClientDetailsHandler extends BaseHandler<ClientDetailsQuery, ClientDetailsResult> {
    private ClientSrv clientSrv;

    public ClientDetailsHandler(UserContext context, ClientSrv clientSrv) {
        super(context);
        this.clientSrv = clientSrv;
    }

    @Override
    public ClientDetailsResult handle(ClientDetailsQuery command) {
        var client = clientSrv.get(context.getUserId());

        var result = new ClientDetailsResult();
        result.setId(client.getId());
        result.setName(client.getName());
        result.setDevices(client
            .getDevices()
            .stream()
            .map(d -> {
                var device = new ClientDetailsDevice();
                device.setId(d.getId());
                device.setName(d.getTypeName());
                device.setCount(d.getCount());

                return device;
            })
            .toList());
        result.setServices(client
            .getServices()
            .stream()
            .map(s -> {
                var service = new ClientDetailsService();
                service.setId(s.getId());
                service.setName(s.getTypeName());

                return service;
            })
            .toList());

        return result;
    }
}
