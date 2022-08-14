package rmm.ninjaone.pricing.data;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.ports.catalog.SubscriptionData;
import rmm.ninjaone.pricing.strategies.MonthlyFixedDevicePricingStrategy;
import rmm.ninjaone.pricing.strategies.MonthlyFixedServicePricingStrategy;
import rmm.ninjaone.pricing.strategies.PerDeviceServicePricingStrategy;
import rmm.ninjaone.pricing.strategies.PerDeviceTypeServicePricingStrategy;
import rmm.ninjaone.pricing.strategies.PerUnitDevicePricingStrategy;
import rmm.ninjaone.pricing.strategies.TieredDevicePricingStrategy;
import rmm.ninjaone.pricing.strategies.TieredServicePricingStrategy;

@SuppressWarnings("unchecked")
public class SubscriptionMother extends ObjectMother {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static SubscriptionData monthlyFixedDevice(double cost) {
        var data = new MonthlyFixedDevicePricingStrategy.Data();
        data.setMonthlyCost(cost);

        var map = (Map<String, Object>)mapper.convertValue(data, Map.class);
        return new SubscriptionData("MonthlyFixedDevice", map);
    }

    public static SubscriptionData perUnitDevice(double cost) {
        var data = new PerUnitDevicePricingStrategy.Data();
        data.setUnitCost(cost);
        
        var map = (Map<String, Object>)mapper.convertValue(data, Map.class);
        return new SubscriptionData("PerUnitDevice", map);
    }

    public static SubscriptionData tieredDevice(int[] counts, double[] costs) {
        var tiers = new ArrayList<TieredDevicePricingStrategy.Data.Tier>();

        var length = Math.min(counts.length, costs.length);
        for (int i = 0; i < length; i++) {
            var tier = new TieredDevicePricingStrategy.Data.Tier();
            tier.setCount(counts[i]);
            tier.setCost(costs[i]);

            tiers.add(tier);
        }

        var data = new TieredDevicePricingStrategy.Data();
        data.setLastCost(random.nextDouble(10));
        data.setTiers(tiers);

        var map = (Map<String, Object>)mapper.convertValue(data, Map.class);
        return new SubscriptionData("TieredDevice", map);
    }

    public static SubscriptionData monthlyFixedService(double cost) {
        var data = new MonthlyFixedServicePricingStrategy.Data();
        data.setMonthlyCost(cost);

        var map = (Map<String, Object>)mapper.convertValue(data, Map.class);
        return new SubscriptionData("MonthlyFixedService", map);
    }

    public static SubscriptionData perDeviceService(double cost) {
        var data = new PerDeviceServicePricingStrategy.Data();
        data.setUnitCost(cost);
        
        var map = (Map<String, Object>)mapper.convertValue(data, Map.class);
        return new SubscriptionData("PerDeviceService", map);
    }

    public static SubscriptionData perDeviceTypeService(UUID[] devices, double[] costs) {
        var types = new ArrayList<PerDeviceTypeServicePricingStrategy.Data.Type>();

        var length = Math.min(devices.length, costs.length);
        for (int i = 0; i < length; i++) {
            var type = new PerDeviceTypeServicePricingStrategy.Data.Type();
            type.setDeviceId(devices[i]);
            type.setCost(costs[i]);

            types.add(type);
        }
        
        var data = new PerDeviceTypeServicePricingStrategy.Data();
        data.setTypes(types);
        
        var map = (Map<String, Object>)mapper.convertValue(data, Map.class);
        return new SubscriptionData("PerDeviceTypeService", map);
    }

    public static SubscriptionData tieredService(int[] counts, double[] costs) {
        var tiers = new ArrayList<TieredServicePricingStrategy.Data.Tier>();

        var length = Math.min(counts.length, costs.length);
        for (int i = 0; i < length; i++) {
            var tier = new TieredServicePricingStrategy.Data.Tier();
            tier.setCount(counts[i]);
            tier.setCost(costs[i]);

            tiers.add(tier);
        }

        var data = new TieredServicePricingStrategy.Data();
        data.setLastCost(random.nextDouble(10));
        data.setTiers(tiers);

        var map = (Map<String, Object>)mapper.convertValue(data, Map.class);
        return new SubscriptionData("TieredService", map);
    }
}
