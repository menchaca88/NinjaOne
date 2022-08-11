package rmm.ninjaone.identity.infrastructure.endpoints.users;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rmm.ninjaone.buildingblocks.infrastructure.api.BaseController;
import rmm.ninjaone.identity.application.commands.DeleteUser.DeleteUserCommand;
import rmm.ninjaone.identity.application.commands.UpdateUser.UpdateUserCommand;
import rmm.ninjaone.identity.application.queries.ListUsers.ListUsersQuery;
import rmm.ninjaone.identity.application.queries.UserDetails.UserDetailsQuery;

@RestController
@RequestMapping(path = "${api.users}")
public class UsersController extends BaseController {
    @GetMapping()
    public ResponseEntity<ListUsersResponse> list() {
        var query = new ListUsersQuery();

        var result = pipeline.send(query);
        var response = modelMapper.map(result, ListUsersResponse.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponse> details(@PathVariable UUID userId) {
        var query = new UserDetailsQuery();
        query.setId(userId);

        var result = pipeline.send(query);
        var response = modelMapper.map(result, UserResponse.class);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID userId, @Valid @RequestBody UpdateUserRequest request) {
        if (!userId.equals(request.getId()))
            throw new IllegalArgumentException("userId");

        var command = new UpdateUserCommand();
        command.setId(request.getId());
        command.setName(request.getName());
        command.setPassword(request.getPassword());

        var result = pipeline.send(command);
        var response = modelMapper.map(result, UserResponse.class);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<UserResponse> delete(@PathVariable UUID userId) {
        var query = new DeleteUserCommand();
        query.setId(userId);

        var result = pipeline.send(query);
        var response = modelMapper.map(result, UserResponse.class);

        return ResponseEntity.ok(response);
    }
}
