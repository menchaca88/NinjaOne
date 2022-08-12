package rmm.ninjaone.catalog.infrastructure.endpoints.devices;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rmm.ninjaone.buildingblocks.infrastructure.api.BaseController;
import rmm.ninjaone.catalog.application.devices.commands.CreateDevice.CreateDeviceCommand;
import rmm.ninjaone.catalog.application.devices.commands.DeleteDevice.DeleteDeviceCommand;
import rmm.ninjaone.catalog.application.devices.commands.UpdateDevice.UpdateDeviceCommand;
import rmm.ninjaone.catalog.application.devices.queries.DeviceDetails.DeviceDetailsQuery;
import rmm.ninjaone.catalog.application.devices.queries.ListDevices.ListDevicesQuery;

@RestController
@RequestMapping(path = "${api.catalog}/devices")
public class DevicesController extends BaseController {
    @GetMapping()
    public ResponseEntity<ListDevicesResponse> list() {
        var query = new ListDevicesQuery();

        var result = pipeline.send(query);
        var response = modelMapper.map(result, ListDevicesResponse.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{deviceId}")
    public ResponseEntity<DeviceResponse> details(@PathVariable UUID deviceId) {
        var query = new DeviceDetailsQuery();
        query.setId(deviceId);

        var result = pipeline.send(query);
        var response = modelMapper.map(result, DeviceResponse.class);

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<DeviceResponse> create(@Valid @RequestBody CreateDeviceRequest request) {
        var command = new CreateDeviceCommand();
        command.setName(request.getName());

        var result = pipeline.send(command);
        var response = modelMapper.map(result, DeviceResponse.class);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{deviceId}")
    public ResponseEntity<DeviceResponse> update(@PathVariable UUID deviceId, @Valid @RequestBody UpdateDeviceRequest request) {
        if (!deviceId.equals(request.getId()))
            throw new IllegalArgumentException("deviceId");

        var command = new UpdateDeviceCommand();
        command.setId(request.getId());
        command.setName(request.getName());

        var result = pipeline.send(command);
        var response = modelMapper.map(result, DeviceResponse.class);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{deviceId}")
    public ResponseEntity<DeviceResponse> delete(@PathVariable UUID deviceId) {
        var query = new DeleteDeviceCommand();
        query.setId(deviceId);

        var result = pipeline.send(query);
        var response = modelMapper.map(result, DeviceResponse.class);

        return ResponseEntity.ok(response);
    }
}
