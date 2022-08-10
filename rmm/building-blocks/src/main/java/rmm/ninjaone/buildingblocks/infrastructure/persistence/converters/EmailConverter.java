package rmm.ninjaone.buildingblocks.infrastructure.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email attribute) {
        return attribute.getAddress();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return Email.of(dbData);
    }
}