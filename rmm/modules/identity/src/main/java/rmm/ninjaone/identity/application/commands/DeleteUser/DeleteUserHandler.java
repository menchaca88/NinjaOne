package rmm.ninjaone.identity.application.commands.DeleteUser;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;
import rmm.ninjaone.identity.domain.specifications.UserSpecifications;

@Component
public class DeleteUserHandler extends BaseHandler<DeleteUserCommand, DeleteUserResult> {
    private final RmmUserRepository repository;

    public DeleteUserHandler(UserContext context, RmmUserRepository repository) {
        super(context);
        this.repository = repository;
    }

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
