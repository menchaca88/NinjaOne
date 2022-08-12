package rmm.ninjaone.buildingblocks.domain.valueObjects;

import java.util.Random;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Sku {
    private final static int SKU_LENGTH = 15;
    private final String raw;

    private Sku(@Size(min = SKU_LENGTH, max = SKU_LENGTH) String raw) {
        this.raw = raw;
    }

    public static Sku of(String raw) {
        return new Sku(raw);
    }

    public static SkuBuilder For(@NotBlank Class<?> clazz) {
        return new SkuBuilder(clazz.getSimpleName());
    }

    public static class SkuBuilder 
    {
        private static Random random = new Random();
        
        private String clazz;
        private String model;
        private String subsc;

        public SkuBuilder(String clazz) {
            this.clazz = clazz;
            this.model = "";
        }

        public SkuBuilder model(@NotBlank String model) {
            this.model = model;
            return this;
        }

        public SkuBuilder price(@NotBlank String priceInfo) {
            this.subsc = priceInfo;
            return this;
        }

        public Sku build() {
            var _0 = clazz.length() < 2 ? clazz : clazz.substring(0, 2);
            var _1 = model.length() < 2 ? model : model.substring(0, 2);
            var _2 = model.length() < 2 ? subsc : subsc.substring(0, 2);
            var _3 = generateTrailingCode(SKU_LENGTH - _0.length() - _1.length() - _2.length());

            var raw = String.format("%s.%s.%s.%s", _0, _1, _2, _3);
            return Sku.of(raw);
        }

        private static String generateTrailingCode(Integer length) {
            String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
            StringBuilder salt = new StringBuilder();
    
            while (salt.length() < length) {
                int index = (int)(random.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
    
            return salt.toString();
        }
    }
}
