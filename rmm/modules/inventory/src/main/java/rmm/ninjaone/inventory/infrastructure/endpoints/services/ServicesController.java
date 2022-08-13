package rmm.ninjaone.inventory.infrastructure.endpoints.services;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rmm.ninjaone.buildingblocks.infrastructure.api.BaseController;
import rmm.ninjaone.inventory.application.services.commands.CreateService.CreateServiceCommand;
import rmm.ninjaone.inventory.application.services.commands.DeleteService.DeleteServiceCommand;
import rmm.ninjaone.inventory.application.services.queries.ListServices.ListServicesQuery;

@RestController("ServicesController")
@RequestMapping(path = "${api.inventory}/services")
public class ServicesController extends BaseController {
    @GetMapping()
    public ResponseEntity<ListServicesResponse> list() {
        var query = new ListServicesQuery();

        var result = pipeline.send(query);
        var response = modelMapper.map(result, ListServicesResponse.class);

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody CreateServiceRequest request) {
        var command = new CreateServiceCommand();
        command.setTypeId(request.getTypeId());

        var result = pipeline.send(command);
        var response = modelMapper.map(result, ServiceResponse.class);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{serviceId}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable UUID serviceId) {
        var query = new DeleteServiceCommand();
        query.setId(serviceId);

        var result = pipeline.send(query);
        var response = modelMapper.map(result, ServiceResponse.class);

        return ResponseEntity.ok(response);
    }
}