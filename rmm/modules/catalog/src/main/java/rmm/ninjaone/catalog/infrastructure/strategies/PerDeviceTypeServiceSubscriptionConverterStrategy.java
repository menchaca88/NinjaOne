package rmm.ninjaone.catalog.infrastructure.strategies;

import java.util.List;
import java.util.UUID;

import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.PerDeviceTypeServiceSubscription;

@Component
public class PerDeviceTypeServiceSubscriptionConverterStrategy extends BaseSubscriptionConverterStrategy<PerDeviceTypeServiceSubscription, PerDeviceTypeServiceSubscriptionConverterStrategy.PerDeviceTypeData> {
    

    public PerDeviceTypeServiceSubscriptionConverterStrategy(ObjectMapper objectMapper, Validator validator) {
        super(objectMapper, validator, PerDeviceTypeServiceSubscription.class, PerDeviceTypeData.class);
    }

    @Override
    protected PerDeviceTypeServiceSubscription convertToSubscription(PerDeviceTypeData data) {
        return new PerDeviceTypeServiceSubscription(data
            .getTypes()
            .stream()
            .map(t -> new PerDeviceTypeServiceSubscription.Type(t.getDeviceId(), t.getCost()))
            .toList());
    }

    @Override
    protected PerDeviceTypeData convertToData(PerDeviceTypeServiceSubscription subcription) {
        return new PerDeviceTypeData(subcription
            .getTypes()
            .stream()
            .map(t -> new PerDeviceTypeData.Type(t.getDeviceId(), t.getCost()))
            .toList());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PerDeviceTypeData {
        @NotEmpty private List<Type> types;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Type {
            @Min(0) private UUID deviceId;
            @Min(0) private double cost;
        }
    }
}
