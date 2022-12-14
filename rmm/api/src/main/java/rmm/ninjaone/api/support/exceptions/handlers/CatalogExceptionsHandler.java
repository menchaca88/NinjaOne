package rmm.ninjaone.api.support.exceptions.handlers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import rmm.ninjaone.api.support.exceptions.ErrorCodes;
import rmm.ninjaone.api.support.exceptions.ErrorResponse;
import rmm.ninjaone.catalog.domain.exceptions.DeviceAlreadyExistsException;
import rmm.ninjaone.catalog.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.catalog.domain.exceptions.DeviceUsedByServiceException;
import rmm.ninjaone.catalog.domain.exceptions.NameAlreadyUsedException;
import rmm.ninjaone.catalog.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.catalog.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;

public interface CatalogExceptionsHandler {
    MessageSource messageSource();

    @ExceptionHandler(value = { NameAlreadyUsedException.class })
    public default ResponseEntity<ErrorResponse> handleNameAlreadyUsedException(NameAlreadyUsedException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.name.alreadyExists", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.NAME_EXISTS);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { DeviceNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handleDeviceNotFoundException(DeviceNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.devices.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.DEVICE_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { DeviceAlreadyExistsException.class })
    public default ResponseEntity<ErrorResponse> handleDeviceAlreadyExistsException(DeviceAlreadyExistsException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.devices.alreadyExists", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.DEVICE_EXISTS);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { DeviceUsedByServiceException.class })
    public default ResponseEntity<ErrorResponse> handleDeviceUsedByServiceException(DeviceUsedByServiceException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.devices.inUse", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.DEVICE_IN_USE);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = { ServiceNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handleServiceNotFoundException(ServiceNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.services.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.SERVICE_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ServiceAlreadyExistsException.class })
    public default ResponseEntity<ErrorResponse> handleServiceAlreadyExistsException(ServiceAlreadyExistsException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.services.alreadyExists", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.SERVICE_EXISTS);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { UnsupportedConversionException.class })
    public default ResponseEntity<ErrorResponse> handleUnsupportedConversionException(UnsupportedConversionException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.subscriptions.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.SUBSCRIPTION_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    
}
