package rmm.ninjaone.catalog.infrastructure.endpoints.subscriptions;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListSubscriptionsResponse {
    private List<String> devices;
    private List<String> services;
}