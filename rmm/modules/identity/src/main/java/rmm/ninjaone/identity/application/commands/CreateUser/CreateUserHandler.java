package rmm.ninjaone.identity.application.commands.CreateUser;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.application.contracts.PasswordEncrypter;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserAlreadyExistsException;
import rmm.ninjaone.identity.domain.models.RmmRole;
import rmm.ninjaone.identity.domain.models.RmmUser;
import rmm.ninjaone.identity.domain.specifications.UserSpecifications;

@Component
@RequiredArgsConstructor
public class CreateUserHandler implements Command.Handler<CreateUserCommand, CreateUserResult> {
    private final RmmUserRepository repository;
    private final PasswordEncrypter passwordEncrypter;

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
