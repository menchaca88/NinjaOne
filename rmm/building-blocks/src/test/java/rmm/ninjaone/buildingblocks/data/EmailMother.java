package rmm.ninjaone.buildingblocks.data;

public class EmailMother extends ObjectMother {
    public static String random() {
        return StringMother.random() + "@gmail.com";
    }
}
