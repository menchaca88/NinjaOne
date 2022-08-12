package rmm.ninjaone.buildingblocks.application.bases;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.application.support.UserContext;

@RequiredArgsConstructor
public abstract class BaseHandler<C extends Command<R>, R> implements Command.Handler<C, R> {
    protected final UserContext context;
}
