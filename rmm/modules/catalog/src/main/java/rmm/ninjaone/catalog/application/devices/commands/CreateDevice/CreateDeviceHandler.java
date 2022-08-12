package rmm.ninjaone.catalog.application.devices.commands.CreateDevice;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;
import rmm.ninjaone.catalog.domain.contracts.subscriptions.SubscriptionSrv;
import rmm.ninjaone.catalog.domain.models.Subscription;

@Component
@RequiredArgsConstructor
public class CreateDeviceHandler implements Command.Handler<CreateDeviceCommand, CreateDeviceResult> {
    private final DeviceSrv deviceSrv;
    private final SubscriptionSrv subscriptionSrv;

    @Override
    public CreateDeviceResult handle(CreateDeviceCommand command) {
        var rawData = new Subscription.RawData();
        rawData.setType(command.getSubscriptionType());
        rawData.setData(command.getSubscriptionData());
        
        var subscription = subscriptionSrv.deviceCreate(rawData);
        var device = deviceSrv.create(command.getName(), subscription);

        var result = new CreateDeviceResult();
        result.setId(device.getId());
        result.setName(device.getName());
        result.setSku(device.getSku().getRaw());
        result.setSubscription(device.getSubscription().getName());
            
        return result;
    }
}
