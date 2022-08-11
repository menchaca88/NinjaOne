package rmm.ninjaone.identity.application.queries.FindUser;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserQuery implements Command<FindUserResult> {
    @NotNull private String email;
}
