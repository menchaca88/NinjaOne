package rmm.ninjaone.buildingblocks.infrastructure.pipeline.middlewares;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;

@Order(3)
@Component
@RequiredArgsConstructor
public class TransactionalMiddleware implements Command.Middleware {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        var response = next.invoke();

        entityManager.flush();

        return response;
    }
}