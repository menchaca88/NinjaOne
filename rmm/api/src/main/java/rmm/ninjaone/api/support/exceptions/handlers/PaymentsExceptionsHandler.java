package rmm.ninjaone.api.support.exceptions.handlers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import rmm.ninjaone.api.support.exceptions.ErrorCodes;
import rmm.ninjaone.api.support.exceptions.ErrorResponse;
import rmm.ninjaone.payments.domain.exceptions.PayerAlreadyExistsException;
import rmm.ninjaone.payments.domain.exceptions.PayerNotFoundException;
import rmm.ninjaone.payments.domain.exceptions.BillAlreadyPaidException;
import rmm.ninjaone.payments.domain.exceptions.DeviceAlreadyExistsException;
import rmm.ninjaone.payments.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.payments.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.payments.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.payments.domain.exceptions.UnknownPriceException;

public interface PaymentsExceptionsHandler {
    MessageSource messageSource();

    @ExceptionHandler(value = { PayerAlreadyExistsException.class })
    public default ResponseEntity<ErrorResponse> handlePayerAlreadyExistsException(PayerAlreadyExistsException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.payers.alreadyExists", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.PAYER_EXISTS);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { PayerNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handlePayerNotFoundException(PayerNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.payers.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.PAYER_NOT_FOUND);

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

    @ExceptionHandler(value = { BillAlreadyPaidException.class })
    public default ResponseEntity<ErrorResponse> handleBillAlreadyPaidException(BillAlreadyPaidException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.bills.alreadyPaid", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.BILL_PAID);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = { UnknownPriceException.class })
    public default ResponseEntity<ErrorResponse> handleUnknownPriceException(UnknownPriceException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.subscriptions.unknownPrice", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.UNKNOWN_PRICE);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
