package rmm.ninjaone.catalog.application.services.commands.CreateService;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.ServiceSrv;

@Component
@RequiredArgsConstructor
public class CreateServiceHandler implements Command.Handler<CreateServiceCommand, CreateServiceResult> {
    private final ServiceSrv serviceSrv;

    @Override
    public CreateServiceResult handle(CreateServiceCommand command) {
        var service = serviceSrv.create(command.getName(), null); //TODO: subscription

        var result = new CreateServiceResult();
        result.setId(service.getId());
        result.setName(service.getName());
        result.setSku(service.getSku().getRaw());
            
        return null;
    }
    
}
