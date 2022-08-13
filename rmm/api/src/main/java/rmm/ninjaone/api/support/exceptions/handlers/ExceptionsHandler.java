package rmm.ninjaone.api.support.exceptions.handlers;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionsHandler implements GeneralExceptionsHandler, InventoryExceptionsHandler, AuthenticationExceptionsHandler, CatalogExceptionsHandler, UsersExceptionsHandler {
    private final MessageSource messageSource;

    @Override
    public MessageSource messageSource() {
        return messageSource;
    }
}
