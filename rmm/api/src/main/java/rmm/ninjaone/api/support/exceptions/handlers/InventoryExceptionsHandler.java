package rmm.ninjaone.api.support.exceptions.handlers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import rmm.ninjaone.api.support.exceptions.ErrorCodes;
import rmm.ninjaone.api.support.exceptions.ErrorResponse;
import rmm.ninjaone.inventory.domain.exceptions.ClientAlreadyExistsException;
import rmm.ninjaone.inventory.domain.exceptions.ClientNotFoundException;
import rmm.ninjaone.inventory.domain.exceptions.DeviceAlreadyExistsException;
import rmm.ninjaone.inventory.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.inventory.domain.exceptions.DeviceTypeNotFoundException;
import rmm.ninjaone.inventory.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.inventory.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.inventory.domain.exceptions.ServiceTypeNotFoundException;

public interface InventoryExceptionsHandler {
    MessageSource messageSource();

    @ExceptionHandler(value = { ClientAlreadyExistsException.class })
    public default ResponseEntity<ErrorResponse> handleClientAlreadyExistsException(ClientAlreadyExistsException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.clients.alreadyExists", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.NAME_EXISTS);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ClientNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handleClientNotFoundException(ClientNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.clients.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.DEVICE_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { DeviceAlreadyExistsException.class })
    public default ResponseEntity<ErrorResponse> handleDeviceAlreadyExistsException(DeviceAlreadyExistsException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.devices.alreadyExists", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.DEVICE_EXISTS);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { DeviceNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handleDeviceNotFoundException(DeviceNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.devices.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.DEVICE_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { DeviceTypeNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handleDeviceTypeNotFoundException(DeviceTypeNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.devices.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.DEVICE_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ServiceAlreadyExistsException.class })
    public default ResponseEntity<ErrorResponse> handleServiceAlreadyExistsException(ServiceAlreadyExistsException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.services.alreadyExists", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.SERVICE_EXISTS);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ServiceNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handleServiceNotFoundException(ServiceNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.services.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.SERVICE_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ServiceTypeNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handleServiceTypeNotFoundException(ServiceTypeNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.services.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.SERVICE_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
