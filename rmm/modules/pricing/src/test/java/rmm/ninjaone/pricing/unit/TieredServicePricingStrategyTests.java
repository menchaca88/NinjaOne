package rmm.ninjaone.pricing.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import rmm.ninjaone.pricing.data.ItemMother;
import rmm.ninjaone.pricing.data.PackageMother;
import rmm.ninjaone.pricing.data.SubscriptionMother;
import rmm.ninjaone.pricing.strategies.TieredServicePricingStrategy;
import rmm.ninjaone.pricing.strategies.PricingStrategy;

@ExtendWith(MockitoExtension.class)
public class TieredServicePricingStrategyTests {
    TieredServicePricingStrategy strategy;

    @BeforeEach
    void setUp() {
        var objectMapper = new ObjectMapper();
        strategy = new TieredServicePricingStrategy(objectMapper);
    }

    @Test
    void supports_TieredDevice_ReturnsTrue() {
        // Arrange
        var item = ItemMother.device();
        var data = SubscriptionMother.tieredService(new int[0], new double[0]);
        var details = new PricingStrategy.DetailedItem(item, data);

        // Act
        var result = strategy.supports(details);

        // Assert
        assertTrue(result);
    }

    @Test
    void calulate_withPackage_ReturnsCorrectPrice() {
        // Arrange
        int[] counts = { 4, 25, 100 };
        double[] costs = { 4, 2, 1 };
        var item = ItemMother.service();
        var data = SubscriptionMother.tieredDevice(counts, costs);
        var pckg = PackageMother.random(item, 2, 5);
        var details = new PricingStrategy.DetailedItem(item, data);

        // Act
        var result = strategy.calculate(pckg, details);

        // Assert
        var total = pckg.getItems()
            .stream()
            .filter(i -> i.hasCount())
            .map(i -> i.getCount())
            .reduce(0, Integer::sum);
        assertEquals(total * 2, result);
    }
}
