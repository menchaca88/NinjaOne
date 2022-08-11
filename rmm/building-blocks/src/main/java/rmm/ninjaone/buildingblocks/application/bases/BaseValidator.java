package rmm.ninjaone.buildingblocks.application.bases;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.application.contracts.CommandValidator;

@RequiredArgsConstructor
public abstract class BaseValidator<C extends Command<R>, R>  implements CommandValidator<C, R> {
    private final Validator validator;

    @Override
    public void validate(C command) {
        var violations = validator.validate(command);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
