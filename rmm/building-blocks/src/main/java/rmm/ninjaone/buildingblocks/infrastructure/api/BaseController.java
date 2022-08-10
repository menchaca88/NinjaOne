package rmm.ninjaone.buildingblocks.infrastructure.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import an.awesome.pipelinr.Pipeline;

public abstract class BaseController {
    @Autowired
    protected Pipeline pipeline;

    @Autowired
    protected ModelMapper modelMapper;
}