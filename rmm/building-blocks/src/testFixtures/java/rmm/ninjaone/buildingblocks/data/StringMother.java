package rmm.ninjaone.buildingblocks.data;

public class StringMother extends ObjectMother {
    public static String random() {
        return random(5, 10);
    }

    public static String random(int min, int max) {
        if (min >= max)
            throw new IllegalArgumentException();

        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();

        int length = random.nextInt(max - min + 1) + min;

        while (salt.length() < length) {
            int index = (int)(random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        return salt.toString();
    }
}