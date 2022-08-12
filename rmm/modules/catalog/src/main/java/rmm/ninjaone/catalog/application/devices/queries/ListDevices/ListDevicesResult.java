package rmm.ninjaone.catalog.application.devices.queries.ListDevices;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class ListDevicesResult extends BaseResult {
    private List<ListDevicesItem> devices;
}
