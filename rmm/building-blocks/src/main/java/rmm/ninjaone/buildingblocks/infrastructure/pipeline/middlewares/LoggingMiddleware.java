package rmm.ninjaone.buildingblocks.infrastructure.pipeline.middlewares;

import java.util.logging.Level;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Log
@Order(1)
@Component
@RequiredArgsConstructor
public class LoggingMiddleware implements Command.Middleware {
    private final ObjectMapper objectMapper;

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        try {
            logCommand(command);
            return next.invoke();
        }
        catch (DomainException ex) {
            throw ex;
        }
        catch (Exception ex) {
            logException(ex);
            throw ex;
        }
    }

    private void logException(Exception ex) {
        log.log(Level.WARNING, ex.getMessage(), ex);
    }

    private <R, C extends Command<R>> void logCommand(C command) {
        String name = command.getClass().getSimpleName();
        String body;
        
        try { body = objectMapper.writeValueAsString(command); }
        catch (Exception ex) { body = command.toString(); }
        
        var message = String.format("Executing %s: %s", name, body);
        log.info(message);
    }
}