package rmm.ninjaone.catalog.infrastructure.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.ObjectProvider;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;

@Converter(autoApply = true)
@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class DeviceSubscriptionConverter implements AttributeConverter<DeviceSubscription, String> {
    private final ObjectProvider<PersistentSubscriptionConverterStrategy> converters;

    @Override
    public String convertToDatabaseColumn(DeviceSubscription attribute) {
        for (PersistentSubscriptionConverterStrategy converter : converters)
            if (converter.matches(attribute)) {
                try {
                    var data = converter.convert(attribute);
                    return data.toString();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        throw new UnsupportedConversionException(attribute.getName());
    }

    @Override
    public DeviceSubscription convertToEntityAttribute(String dbData) {
        var data = SubscriptionEntity.parse(dbData);
        if (data != null) {
            for (PersistentSubscriptionConverterStrategy converter : converters)
                if (converter.matches(data)) {
                    try {
                        return (DeviceSubscription)converter.convert(data);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            throw new UnsupportedConversionException(data.getType());
        }

        throw new UnsupportedConversionException();
    }
}