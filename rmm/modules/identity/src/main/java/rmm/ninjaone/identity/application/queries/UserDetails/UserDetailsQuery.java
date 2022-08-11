package rmm.ninjaone.identity.application.queries.UserDetails;

import java.util.UUID;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsQuery implements Command<UserDetailsResult> {
    private UUID id;
    private String email;
}
