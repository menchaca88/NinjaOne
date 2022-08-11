package rmm.ninjaone.buildingblocks.application.contracts;

public interface PasswordEncrypter {
    String encode(CharSequence rawPassword);
}
