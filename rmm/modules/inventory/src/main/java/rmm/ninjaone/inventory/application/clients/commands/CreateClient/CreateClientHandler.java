package rmm.ninjaone.inventory.application.clients.commands.CreateClient;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientSrv;

@Component
public class CreateClientHandler extends BaseHandler<CreateClientCommand, CreateClientResult> {
    private ClientSrv clientSrv;

    public CreateClientHandler(UserContext context, ClientSrv clientSrv) {
        super(context);
        this.clientSrv = clientSrv;
    }

    @Override
    public CreateClientResult handle(CreateClientCommand command) {
        var client = clientSrv.create(command.getId(), command.getName());

        var result = new CreateClientResult();
        result.setId(client.getId());
        result.setName(client.getName());

        return result;
    }
}
