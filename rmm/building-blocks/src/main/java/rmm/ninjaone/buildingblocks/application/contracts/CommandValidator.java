package rmm.ninjaone.buildingblocks.application.contracts;

import com.google.common.reflect.TypeToken;

import an.awesome.pipelinr.Command;

public interface CommandValidator<C extends Command<R>, R> {
    void validate(C command);

    default boolean matches(C command) {
        TypeToken<C> typeToken = new TypeToken<C>(getClass()){};
        return typeToken.isSupertypeOf(command.getClass());
    }
}
