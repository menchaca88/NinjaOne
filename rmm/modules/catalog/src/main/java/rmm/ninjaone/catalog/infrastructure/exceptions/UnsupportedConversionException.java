package rmm.ninjaone.catalog.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnsupportedConversionException extends RuntimeException {
    private String type;
}
