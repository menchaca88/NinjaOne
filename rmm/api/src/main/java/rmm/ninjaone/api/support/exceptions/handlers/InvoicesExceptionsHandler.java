package rmm.ninjaone.api.support.exceptions.handlers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import rmm.ninjaone.api.support.exceptions.ErrorCodes;
import rmm.ninjaone.api.support.exceptions.ErrorResponse;
import rmm.ninjaone.invoices.exceptions.InvoiceNotFoundException;

public interface InvoicesExceptionsHandler {
    MessageSource messageSource();

    @ExceptionHandler(value = { InvoiceNotFoundException.class })
    public default ResponseEntity<ErrorResponse> handleInvoiceNotFoundException(InvoiceNotFoundException ex, WebRequest request, Locale locale) {
       var message = messageSource().getMessage("errors.invoices.notFound", null, locale);

        ErrorResponse response = new ErrorResponse(message, ErrorCodes.INVOICE_NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}