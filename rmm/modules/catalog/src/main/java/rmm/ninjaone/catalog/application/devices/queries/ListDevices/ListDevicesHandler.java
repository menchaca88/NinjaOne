package rmm.ninjaone.catalog.application.devices.queries.ListDevices;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;

@Component
public class ListDevicesHandler extends BaseHandler<ListDevicesQuery, ListDevicesResult> {
    private final DeviceSrv deviceSrv;

    public ListDevicesHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

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
