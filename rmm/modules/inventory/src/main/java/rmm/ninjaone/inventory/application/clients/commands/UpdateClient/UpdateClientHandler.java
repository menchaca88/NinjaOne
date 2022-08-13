package rmm.ninjaone.inventory.application.clients.commands.UpdateClient;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientSrv;

@Component
public class UpdateClientHandler extends BaseHandler<UpdateClientCommand, UpdateClientResult> {
    private ClientSrv clientSrv;

    public UpdateClientHandler(UserContext context, ClientSrv clientSrv) {
        super(context);
        this.clientSrv = clientSrv;
    }

    @Override
    public UpdateClientResult handle(UpdateClientCommand command) {
        var client = clientSrv.update(command.getId(), command.getName());

        var result = new UpdateClientResult();
        result.setId(client.getId());
        result.setName(client.getName());

        return result;
    }
}
