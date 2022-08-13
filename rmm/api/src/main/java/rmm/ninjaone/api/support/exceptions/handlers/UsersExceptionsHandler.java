package rmm.ninjaone.api.support.exceptions.handlers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import rmm.ninjaone.api.support.exceptions.ErrorCodes;
import rmm.ninjaone.api.support.exceptions.ErrorResponse;
import rmm.ninjaone.identity.domain.exceptions.UserAlreadyExistsException;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;

public interface UsersExceptionsHandler {
    MessageSource messageSource();

    @ExceptionHandler(value = { UserNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.users.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.USER_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { UserAlreadyExistsException.class })
    public default ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.users.alreadyExists", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.USER_EXISTS);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
