package rmm.ninjaone.buildingblocks.domain.contracts;

public interface PasswordEncrypter {
    String encode(CharSequence rawPassword);
}
