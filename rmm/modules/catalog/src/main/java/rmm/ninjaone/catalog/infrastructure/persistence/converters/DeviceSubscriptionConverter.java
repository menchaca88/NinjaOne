package rmm.ninjaone.catalog.infrastructure.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.ObjectProvider;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;
import rmm.ninjaone.catalog.infrastructure.exceptions.UnsupportedConversionException;
import rmm.ninjaone.catalog.infrastructure.persistence.data.SubscriptionEntity;
import rmm.ninjaone.catalog.infrastructure.persistence.strategies.SubscriptionConverterStrategy;

@Converter(autoApply = true)
@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class DeviceSubscriptionConverter implements AttributeConverter<DeviceSubscription, String> {
    private final ObjectProvider<SubscriptionConverterStrategy> converters;

    @Override
    public String convertToDatabaseColumn(DeviceSubscription attribute) {
        for (SubscriptionConverterStrategy converter : converters)
            if (converter.matches(attribute)) {
                try {
                    var entity = converter.convert(attribute);
                    return entity.toString();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        throw new UnsupportedConversionException(attribute.getName());
    }

    @Override
    public DeviceSubscription convertToEntityAttribute(String dbData) {
        var entity = SubscriptionEntity.parse(dbData);
        if (entity != null) {
            for (SubscriptionConverterStrategy converter : converters)
                if (converter.matches(entity)) {
                    try {
                        return (DeviceSubscription)converter.convert(entity);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            throw new UnsupportedConversionException(entity.getType());
        }

        throw new UnsupportedConversionException();
    }
}