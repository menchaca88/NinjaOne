package rmm.ninjaone.identity.application.commands.UpdateUser;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.contracts.PasswordEncrypter;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;
import rmm.ninjaone.identity.domain.specifications.UserSpecifications;

@Component
public class UpdateUserHandler extends BaseHandler<UpdateUserCommand, UpdateUserResult> {
    private final RmmUserRepository repository;
    private final PasswordEncrypter passwordEncrypter;

    public UpdateUserHandler(UserContext context, RmmUserRepository repository, PasswordEncrypter passwordEncrypter) {
        super(context);
        this.repository = repository;
        this.passwordEncrypter = passwordEncrypter;
    }

    @Override
    public UpdateUserResult handle(UpdateUserCommand command) {
        var user = repository
            .findOne(UserSpecifications.findById(command.getId()))
            .orElseThrow(() -> new UserNotFoundException(command.getId()));

        user.rename(command.getName());

        if (command.getPassword() != null) {
            var encodedPassword = passwordEncrypter.encode(command.getPassword());
            user.changePassword(encodedPassword);
        }    
        
        user = repository.save(user);
        
        var result = new UpdateUserResult();
        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail().getAddress());
        result.setRole(user.getRole().name());

        return result;
    }
}
