package rmm.ninjaone.inventory.application.clients.commands.DeleteClient;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientSrv;

@Component
public class DeleteClientHandler extends BaseHandler<DeleteClientCommand, DeleteClientResult> {
    private ClientSrv clientSrv;

    public DeleteClientHandler(UserContext context, ClientSrv clientSrv) {
        super(context);
        this.clientSrv = clientSrv;
    }

    @Override
    public DeleteClientResult handle(DeleteClientCommand command) {
        var client = clientSrv.delete(command.getId());

        var result = new DeleteClientResult();
        result.setId(client.getId());
        result.setName(client.getName());

        return result;
    }
}
