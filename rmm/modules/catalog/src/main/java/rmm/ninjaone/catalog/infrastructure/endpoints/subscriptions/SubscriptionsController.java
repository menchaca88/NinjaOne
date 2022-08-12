package rmm.ninjaone.catalog.infrastructure.endpoints.subscriptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rmm.ninjaone.buildingblocks.infrastructure.api.BaseController;
import rmm.ninjaone.catalog.application.subscriptions.queries.ListSubscriptionTypes.ListSubscriptionTypesQuery;

@RestController
@RequestMapping(path = "${api.catalog}/subscriptions")
public class SubscriptionsController extends BaseController {
    @GetMapping()
    public ResponseEntity<ListSubscriptionsResponse> list() {
        var query = new ListSubscriptionTypesQuery();

        var result = pipeline.send(query);
        var response = modelMapper.map(result, ListSubscriptionsResponse.class);

        return ResponseEntity.ok(response);
    }
}
