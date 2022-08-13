package rmm.ninjaone.catalog.domain.services;

import java.lang.reflect.Modifier;
import java.util.List;

import org.reflections.Reflections;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.subscriptions.SubscriptionConverterStrategy;
import rmm.ninjaone.catalog.domain.contracts.subscriptions.SubscriptionSrv;
import rmm.ninjaone.catalog.domain.models.Subscription.RawData;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.ServiceSubscription;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;

@Service
@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class SubscriptionSrvImpl implements SubscriptionSrv {
    private final ObjectProvider<SubscriptionConverterStrategy> converters;

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

    @Override
    public DeviceSubscription deviceCreate(RawData data) {
        for (SubscriptionConverterStrategy converter : converters)
            if (converter.matches(data)) 
                    return (DeviceSubscription)converter.convert(data);

        throw new UnsupportedConversionException(data.getType());
    }

    @Override
    public ServiceSubscription serviceCreate(RawData data) {
        for (SubscriptionConverterStrategy converter : converters)
            if (converter.matches(data)) 
                    return (ServiceSubscription)converter.convert(data);

        throw new UnsupportedConversionException(data.getType());
    }
}
