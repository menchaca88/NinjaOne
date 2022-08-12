package rmm.ninjaone.catalog.domain.services;

import java.lang.reflect.Modifier;
import java.util.List;

import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import rmm.ninjaone.catalog.domain.contracts.SubscriptionSrv;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.ServiceSubscription;

@Service
public class SubscriptionSrvImpl implements SubscriptionSrv {

    @Override
    public List<String> getDeviceTypes() {
        return getImplementationNamesOf(DeviceSubscription.class);
    }

    @Override
    public List<String> getServiceTypes() {
        return getImplementationNamesOf(ServiceSubscription.class);
    }
    
    private List<String> getImplementationNamesOf(Class<?> clazz) {
        var reflections = new Reflections(clazz.getPackageName());

        var subTypes =  reflections
            .getSubTypesOf(clazz)
            .stream()
            .filter(c -> !Modifier.isAbstract(c.getModifiers()))
            .map(c -> c.getSimpleName())
            .toList();

        return subTypes;
    }
}
