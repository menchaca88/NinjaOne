package rmm.ninjaone.identity.application.commands.DeleteUser;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;
import rmm.ninjaone.identity.domain.specifications.UserSpecifications;

@Component
@RequiredArgsConstructor
public class DeleteUserHandler implements Command.Handler<DeleteUserCommand, DeleteUserResult> {
    private final RmmUserRepository repository;

    @Override
    public DeleteUserResult handle(DeleteUserCommand command) {
        var user = repository
            .findOne(UserSpecifications.findById(command.getId()))
            .orElseThrow(() -> new UserNotFoundException(command.getId()));

        repository.delete(user);
        
        var result = new DeleteUserResult();
        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail().getAddress());
        result.setRole(user.getRole().name());

        return result;
    }
}
