package rmm.ninjaone.api.support.exceptions.handlers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.auth0.jwt.exceptions.TokenExpiredException;

import rmm.ninjaone.api.support.exceptions.ErrorCodes;
import rmm.ninjaone.api.support.exceptions.ErrorResponse;

public interface AuthenticationExceptionsHandler {
    MessageSource messageSource();

    @ExceptionHandler(value = { AuthenticationException.class })
    public default ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.credentials.invalid", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.BAD_CREDENTIALS);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(value = { TokenExpiredException.class })
    public default ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.credentials.invalid", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.BAD_CREDENTIALS);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
