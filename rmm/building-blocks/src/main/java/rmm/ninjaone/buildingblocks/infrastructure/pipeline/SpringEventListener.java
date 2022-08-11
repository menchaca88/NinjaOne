package rmm.ninjaone.buildingblocks.infrastructure.pipeline;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.bases.Event;

@Component
@RequiredArgsConstructor
public class SpringEventListener {
    private final Pipeline pipeline;

    @EventListener
    public void handleContextStart(Event event) {
        pipeline.send(event);
    }
}