package rmm.ninjaone.buildingblocks.domain.valueObjects;

import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Email {
    private static Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final String address;

    private Email(String address) {
        if (!validate(address))
            throw new IllegalArgumentException();

        this.address = address;
    }

    public static Email of(String address) {
        return new Email(address);
    }

    private static boolean validate(String address) {
        return EMAIL_PATTERN.matcher(address).matches();
    }
}
