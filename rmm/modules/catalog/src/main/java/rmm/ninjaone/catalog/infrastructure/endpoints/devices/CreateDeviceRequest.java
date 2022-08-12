package rmm.ninjaone.catalog.infrastructure.endpoints.devices;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import rmm.ninjaone.catalog.infrastructure.endpoints.subscriptions.SubscriptionRequest;

@Data
public class CreateDeviceRequest {
    @NotBlank(message = "{errors.name.required}")
    private String name;
    @NotNull(message = "{errors.subscriptions.required}")
    private SubscriptionRequest subscription;
}
