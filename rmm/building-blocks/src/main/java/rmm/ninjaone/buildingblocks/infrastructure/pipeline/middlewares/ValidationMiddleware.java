package rmm.ninjaone.buildingblocks.infrastructure.pipeline.middlewares;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import rmm.ninjaone.buildingblocks.application.contracts.CommandValidator;

@Order(2)
@Component
@SuppressWarnings("rawtypes")
public class ValidationMiddleware implements Command.Middleware {
    private final ObjectProvider<CommandValidator> validators;
 
    ValidationMiddleware(ObjectProvider<CommandValidator> validators) {
       this.validators = validators;
    }
 
     @Override
     public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        for (var v : validators) {
            @SuppressWarnings("unchecked")
            CommandValidator<C, R> validator = v;
            if (validator.matches(command))
                validator.validate(command);
        }

        return next.invoke();
    }
}