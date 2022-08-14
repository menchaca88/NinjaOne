package rmm.ninjaone.api.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import rmm.ninjaone.api.endpoints.authentication.LoginUserRequest;
import rmm.ninjaone.api.endpoints.authentication.RegisterUserRequest;
import rmm.ninjaone.api.support.setup.RootAccount;
import rmm.ninjaone.catalog.infrastructure.endpoints.subscriptions.SubscriptionRequest;

public class AcceptanceData {
    public static LoginUserRequest loginRoot(RootAccount account) {
        var request = new LoginUserRequest();
        request.setEmail(account.getEmail());
        request.setPassword(account.getPassword());

        return request;
    }

    public static RegisterUserRequest registerUser() {
        var request = new RegisterUserRequest();
        request.setName("Test User");
        request.setEmail("testuser@ninjaone.rmm");
        request.setPassword("password");

        return request;
    }

    public static LoginUserRequest loginUser() {
        var request = new LoginUserRequest();
        request.setEmail("testuser@ninjaone.rmm");
        request.setPassword("password");

        return request;
    }

    public static rmm.ninjaone.catalog.infrastructure.endpoints.devices.CreateDeviceRequest createWindows() {
        var map = new HashMap<String, Object>();
        map.put("unitCost", 4.0);

        var subscription = new SubscriptionRequest();
        subscription.setType("PerUnitDeviceSubscription");
        subscription.setData(map);

        var request = new rmm.ninjaone.catalog.infrastructure.endpoints.devices.CreateDeviceRequest();
        request.setName("Windows");
        request.setSubscription(subscription);

        return request;
    }

    public static rmm.ninjaone.catalog.infrastructure.endpoints.devices.CreateDeviceRequest createMac() {
        var map = new HashMap<String, Object>();
        map.put("unitCost", 4.0);

        var subscription = new SubscriptionRequest();
        subscription.setType("PerUnitDeviceSubscription");
        subscription.setData(map);

        var request = new rmm.ninjaone.catalog.infrastructure.endpoints.devices.CreateDeviceRequest();
        request.setName("Mac");
        request.setSubscription(subscription);

        return request;
    }

    public static rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest createAntivirus(UUID windows, UUID mac) {
        var map = new HashMap<String, Object>();

        var mapWindows = new HashMap<String, Object>();
        mapWindows.put("deviceId", windows);
        mapWindows.put("cost", 5.0);

        var mapMac = new HashMap<String, Object>();
        mapMac.put("deviceId", mac);
        mapMac.put("cost", 7.0);

        var types = new Map[] { mapWindows, mapMac };
        map.put("types", types);

        var subscription = new SubscriptionRequest();
        subscription.setType("PerDeviceTypeServiceSubscription");
        subscription.setData(map);

        var request = new rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest();
        request.setName("Antivirus");
        request.setSubscription(subscription);

        return request;
    }

    public static rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest createBackup() {
        var map = new HashMap<String, Object>();
        map.put("unitCost", 3.0);

        var subscription = new SubscriptionRequest();
        subscription.setType("PerDeviceServiceSubscription");
        subscription.setData(map);

        var request = new rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest();
        request.setName("Backup");
        request.setSubscription(subscription);

        return request;
    }

    public static rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest createPSA() {
        var map = new HashMap<String, Object>();
        map.put("unitCost", 2.0);

        var subscription = new SubscriptionRequest();
        subscription.setType("PerDeviceServiceSubscription");
        subscription.setData(map);

        var request = new rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest();
        request.setName("PSA");
        request.setSubscription(subscription);

        return request;
    }

    public static rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest createScreenShare() {
        var map = new HashMap<String, Object>();
        map.put("unitCost", 1.0);

        var subscription = new SubscriptionRequest();
        subscription.setType("PerDeviceServiceSubscription");
        subscription.setData(map);

        var request = new rmm.ninjaone.catalog.infrastructure.endpoints.services.CreateServiceRequest();
        request.setName("Screen Share");
        request.setSubscription(subscription);

        return request;
    }

    public static rmm.ninjaone.inventory.infrastructure.endpoints.devices.CreateDeviceRequest createDevice(UUID deviceId, int count) {
        var request = new rmm.ninjaone.inventory.infrastructure.endpoints.devices.CreateDeviceRequest();
        request.setTypeId(deviceId);
        request.setCount(count);

        return request;
    }

    public static rmm.ninjaone.inventory.infrastructure.endpoints.services.CreateServiceRequest createService(UUID serviceId) {
        var request = new rmm.ninjaone.inventory.infrastructure.endpoints.services.CreateServiceRequest();
        request.setTypeId(serviceId);

        return request;
    }
}
