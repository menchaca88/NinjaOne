package rmm.ninjaone.identity.application.queries.UserDetails;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;
import rmm.ninjaone.identity.domain.models.RmmUser;
import rmm.ninjaone.identity.domain.specifications.UserSpecifications;

@Component
public class UserDetailsHandler extends BaseHandler<UserDetailsQuery, UserDetailsResult> {
    private final RmmUserRepository repository;

    public UserDetailsHandler(UserContext context, RmmUserRepository repository) {
        super(context);
        this.repository = repository;
    }

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
