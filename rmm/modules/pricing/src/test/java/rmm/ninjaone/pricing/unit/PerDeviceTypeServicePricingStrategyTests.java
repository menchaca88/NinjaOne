package rmm.ninjaone.pricing.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import rmm.ninjaone.buildingblocks.ports.pricing.Item;
import rmm.ninjaone.pricing.data.ItemMother;
import rmm.ninjaone.pricing.data.PackageMother;
import rmm.ninjaone.pricing.data.SubscriptionMother;
import rmm.ninjaone.pricing.strategies.PerDeviceTypeServicePricingStrategy;
import rmm.ninjaone.pricing.strategies.PricingStrategy;

@ExtendWith(MockitoExtension.class)
public class PerDeviceTypeServicePricingStrategyTests {
    PerDeviceTypeServicePricingStrategy strategy;

    @BeforeEach
    void setUp() {
        var objectMapper = new ObjectMapper();
        strategy = new PerDeviceTypeServicePricingStrategy(objectMapper);
    }

    @Test
    void supports_TieredDevice_ReturnsTrue() {
        // Arrange
        var item = ItemMother.device();
        var data = SubscriptionMother.perDeviceTypeService(new UUID[0], new double[0]);
        var details = new PricingStrategy.DetailedItem(item, data);

        // Act
        var result = strategy.supports(details);

        // Assert
        assertTrue(result);
    }

    @Test
    void calulate_withPackage_ReturnsCorrectPrice() {
        // Arrange
        double[] costs = { 4, 2, 1 };
        Item[] items = { ItemMother.device(), ItemMother.device(), ItemMother.device() };
        UUID[] ids = Arrays.stream(items).map(Item::getTypeId).toArray(UUID[]::new);

        var item = ItemMother.service();
        var pckg = PackageMother.random(item, items);  
        var data = SubscriptionMother.perDeviceTypeService(ids, costs);      
        var details = new PricingStrategy.DetailedItem(item, data);

        // Act
        var result = strategy.calculate(pckg, details);

        // Assert
        var total = 0.0;
        for (int i = 0; i < items.length; i++)
            total += costs[i] * items[i].getCount();

        assertEquals(total, result);
    }
}
