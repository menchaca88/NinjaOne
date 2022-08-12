package rmm.ninjaone.identity.application.commands.CreateUser;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.contracts.PasswordEncrypter;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserAlreadyExistsException;
import rmm.ninjaone.identity.domain.models.RmmRole;
import rmm.ninjaone.identity.domain.models.RmmUser;
import rmm.ninjaone.identity.domain.specifications.UserSpecifications;

@Component
public class CreateUserHandler extends BaseHandler<CreateUserCommand, CreateUserResult> {
    private final RmmUserRepository repository;
    private final PasswordEncrypter passwordEncrypter;

    public CreateUserHandler(UserContext context, RmmUserRepository repository, PasswordEncrypter passwordEncrypter) {
        super(context);
        this.repository = repository;
        this.passwordEncrypter = passwordEncrypter;
    }

    @Override
    public CreateUserResult handle(CreateUserCommand command) {
        if (repository.exists(UserSpecifications.findByEmail(Email.of(command.getEmail()))))
            throw new UserAlreadyExistsException(command.getEmail());

        var user = RmmUser.create(
            command.getName(),
            RmmRole.valueOf(command.getRole()),
            Email.of(command.getEmail()));

        var encodedPassword = passwordEncrypter.encode(command.getPassword());
        user.changePassword(encodedPassword);

        user = repository.save(user);
        
        var result = new CreateUserResult();
        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail().getAddress());
        result.setRole(user.getRole().name());

        return result;
    }
}
