package rmm.ninjaone.buildingblocks.infrastructure.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.application.contracts.PasswordEncrypter;

@Service
@RequiredArgsConstructor
public class BCryptPasswordEncrypter implements PasswordEncrypter, PasswordEncoder {
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
		return passwordEncoder.upgradeEncoding(encodedPassword);
    }
}
