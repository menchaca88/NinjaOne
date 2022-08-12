package rmm.ninjaone.identity.application.queries.FindUser;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.models.RmmUser;
import rmm.ninjaone.identity.domain.specifications.UserSpecifications;

@Component
public class FindUserHandler extends BaseHandler<FindUserQuery, FindUserResult> {
    private final RmmUserRepository repository;

    public FindUserHandler(UserContext context, RmmUserRepository repository) {
        super(context);
        this.repository = repository;
    }

    @Override
    public FindUserResult handle(FindUserQuery command) {
        RmmUser user = repository
            .findOne(UserSpecifications.findByEmail(Email.of(command.getEmail())))
            .orElse(null);
        
        if (user == null)
            return null;

        var result = new FindUserResult();

        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail().getAddress());
        result.setRole(user.getRole().name());

        return result;
    }
}
