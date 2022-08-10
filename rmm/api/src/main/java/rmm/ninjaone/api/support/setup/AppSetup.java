package rmm.ninjaone.api.support.setup;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.security.Authorities;
import rmm.ninjaone.api.services.AuthenticationService;
import rmm.ninjaone.identity.domain.exceptions.UserAlreadyExistsException;

@Component
@RequiredArgsConstructor
public class AppSetup implements ApplicationRunner {
    private final AuthenticationService service;
    private final RootAccount account;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            service.register(account.getName(), Authorities.Admin, account.getEmail(), account.getPassword());
        } catch (UserAlreadyExistsException ex) {
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        
    }
}