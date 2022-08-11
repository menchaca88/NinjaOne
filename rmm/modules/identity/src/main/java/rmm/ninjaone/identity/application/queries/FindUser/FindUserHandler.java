package rmm.ninjaone.identity.application.queries.FindUser;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.models.RmmUser;
import rmm.ninjaone.identity.domain.specifications.UserSpecifications;

@Component
@RequiredArgsConstructor
public class FindUserHandler implements Command.Handler<FindUserQuery, FindUserResult> {
    private final RmmUserRepository repository;

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
