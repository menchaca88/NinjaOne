package rmm.ninjaone.buildingblocks.infrastructure.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;

@Converter(autoApply = true)
public class SkuConverter implements AttributeConverter<Sku, String> {

    @Override
    public String convertToDatabaseColumn(Sku attribute) {
        return attribute.getRaw();
    }

    @Override
    public Sku convertToEntityAttribute(String dbData) {
        return Sku.of(dbData);
    }
}