package rmm.ninjaone.catalog.application.services.commands.DeleteService;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;

@Component
@RequiredArgsConstructor
public class DeleteServiceHandler implements Command.Handler<DeleteServiceCommand, DeleteServiceResult> {
    private final ServiceSrv serviceSrv;

    @Override
    public DeleteServiceResult handle(DeleteServiceCommand command) {
        var service = serviceSrv.delete(command.getId());

        var result = new DeleteServiceResult();
        result.setId(service.getId());
        result.setName(service.getName());
        result.setSku(service.getSku().getRaw());
        result.setSubscription(service.getSubscription().getName());
            
        return result;
    }
}
