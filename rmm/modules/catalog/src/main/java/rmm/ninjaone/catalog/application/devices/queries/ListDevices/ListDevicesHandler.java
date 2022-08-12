package rmm.ninjaone.catalog.application.devices.queries.ListDevices;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;

@Component
@RequiredArgsConstructor
public class ListDevicesHandler implements Command.Handler<ListDevicesQuery, ListDevicesResult> {
    private final DeviceSrv deviceSrv;

    @Override
    public ListDevicesResult handle(ListDevicesQuery command) {
        var devices = deviceSrv.getAll();

        var items = devices
            .stream()
            .map(u -> {
                var device = new ListDevicesItem();
                device.setId(u.getId());
                device.setName(u.getName());
                device.setSku(u.getSku().getRaw());
                device.setSubscription(u.getSubscription().getName());

                return device;
            })
            .toList();

        var result = new ListDevicesResult();
        result.setDevices(items);

        return result;
    }
}
