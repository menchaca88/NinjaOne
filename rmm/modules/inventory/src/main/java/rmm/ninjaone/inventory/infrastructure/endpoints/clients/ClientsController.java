package rmm.ninjaone.inventory.infrastructure.endpoints.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rmm.ninjaone.buildingblocks.infrastructure.api.BaseController;
import rmm.ninjaone.inventory.application.clients.queries.ClientDetails.ClientDetailsQuery;

@RestController
@RequestMapping(path = "${api.inventory}")
public class ClientsController extends BaseController {
    @GetMapping
    public ResponseEntity<ClientResponse> details() {
        var query = new ClientDetailsQuery();

        var result = pipeline.send(query);
        var response = modelMapper.map(result, ClientResponse.class);

        return ResponseEntity.ok(response);
    }
}