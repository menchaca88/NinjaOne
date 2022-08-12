package rmm.ninjaone.catalog.infrastructure.endpoints.subscriptions;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SubscriptionRequest {
    @NotBlank(message = "{errors.subscriptions.type.required}")
    private String type;
    @NotNull(message = "{errors.subscriptions.data.required}")
    private Map<String, Object> data;
}
