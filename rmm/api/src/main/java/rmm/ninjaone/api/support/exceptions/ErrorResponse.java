package rmm.ninjaone.api.support.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String message;
    private final String error;
}
