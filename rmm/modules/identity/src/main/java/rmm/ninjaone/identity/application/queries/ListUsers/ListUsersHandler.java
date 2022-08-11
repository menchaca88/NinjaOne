package rmm.ninjaone.identity.application.queries.ListUsers;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;

@Component
@RequiredArgsConstructor
public class ListUsersHandler implements Command.Handler<ListUsersQuery, ListUsersResult> {
    private final RmmUserRepository repository;

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
