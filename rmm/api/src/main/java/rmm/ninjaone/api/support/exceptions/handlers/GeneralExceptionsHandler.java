package rmm.ninjaone.api.support.exceptions.handlers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.support.exceptions.ErrorCodes;
import rmm.ninjaone.api.support.exceptions.ErrorResponse;

@ControllerAdvice
@RequiredArgsConstructor
public class GeneralExceptionsHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorResponse> handleAnyException(Exception ex, WebRequest request) {
        String message = ex.getLocalizedMessage();
        if (message == null || message.isEmpty())
            message = ex.toString();

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.UNKNOWN_ERROR);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<ErrorResponse> handleJsonMappingException(HttpMessageNotReadableException ex, WebRequest request, Locale locale) {
        var message = messageSource.getMessage("errors.payload.invalid", null, locale);
            
        ErrorResponse response = new ErrorResponse(message, ErrorCodes.BAD_REQUEST);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        var message = ex.getFieldError().getDefaultMessage();

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.BAD_REQUEST);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
    }
}