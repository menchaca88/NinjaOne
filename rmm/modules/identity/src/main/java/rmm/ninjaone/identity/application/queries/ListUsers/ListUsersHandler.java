package rmm.ninjaone.identity.application.queries.ListUsers;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;

@Component
public class ListUsersHandler extends BaseHandler<ListUsersQuery, ListUsersResult> {
    private final RmmUserRepository repository;

    public ListUsersHandler(UserContext context, RmmUserRepository repository) {
        super(context);
        this.repository = repository;
    }

    @Override
    public ListUsersResult handle(ListUsersQuery command) {
        var users = repository.findAll();

        var items = users
            .stream()
            .map(u -> {
                var user = new ListUsersItem();
                user.setId(u.getId());
                user.setName(u.getName());
                user.setEmail(u.getEmail().getAddress());
                user.setRole(u.getRole().name());

                return user;
            })
            .toList();

        var result = new ListUsersResult();
        result.setUsers(items);

        return result;
    }
}
