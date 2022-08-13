package rmm.ninjaone.inventory.application.devices.queries.ListDevices;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.devices.DeviceSrv;

@Component("ListDevicesHandler")
public class ListDevicesHandler extends BaseHandler<ListDevicesQuery, ListDevicesResult> {
    private DeviceSrv deviceSrv;

    public ListDevicesHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public ListDevicesResult handle(ListDevicesQuery command) {
        var devices = deviceSrv.getAll(context.getUserId());

        var result = new ListDevicesResult();
        result.setDevices(devices
            .stream()
            .map(d -> { 
                var device = new ListDevicesItem();
                device.setId(d.getId());
                device.setCount(d.getCount());
                device.setName(d.getTypeName());

                return device;
            })
            .toList());

        return result;
    }
}
