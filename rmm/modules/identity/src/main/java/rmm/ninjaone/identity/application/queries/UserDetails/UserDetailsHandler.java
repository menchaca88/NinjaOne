package rmm.ninjaone.identity.application.queries.UserDetails;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;
import rmm.ninjaone.identity.domain.models.RmmUser;
import rmm.ninjaone.identity.domain.specifications.UserSpecifications;

@Component
@RequiredArgsConstructor
public class UserDetailsHandler implements Command.Handler<UserDetailsQuery, UserDetailsResult> {
    private final RmmUserRepository repository;

    @Override
    public UserDetailsResult handle(UserDetailsQuery command) {
        RmmUser user = null;
        
        if (command.getId() != null)
            user = repository
                .findOne(UserSpecifications.findById(command.getId()))
                .orElseThrow(() -> new UserNotFoundException(command.getId()));
        else if (command.getEmail() != null)
            user = repository
                .findOne(UserSpecifications.findByEmail(Email.of(command.getEmail())))
                .orElseThrow(() -> new UserNotFoundException(command.getEmail()));

        var result = new UserDetailsResult();

        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail().getAddress());
        result.setPassword(user.getPassword());
        result.setRole(user.getRole().name());

        return result;
    }
}
