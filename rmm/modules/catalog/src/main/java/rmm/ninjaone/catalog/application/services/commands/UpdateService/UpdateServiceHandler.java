package rmm.ninjaone.catalog.application.services.commands.UpdateService;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;

@Component
@RequiredArgsConstructor
public class UpdateServiceHandler implements Command.Handler<UpdateServiceCommand, UpdateServiceResult> {
    private final ServiceSrv serviceSrv;

    @Override
    public UpdateServiceResult handle(UpdateServiceCommand command) {
        var service = serviceSrv.update(command.getId(), command.getName());

        var result = new UpdateServiceResult();
        result.setId(service.getId());
        result.setName(service.getName());
        result.setSku(service.getSku().getRaw());
        result.setSubscription(service.getSubscription().getName());
            
        return result;
    }
}
