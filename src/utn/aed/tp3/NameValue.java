package utn.aed.tp3;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 */
public class NameValue {
    private final String name;
    private final int value;
    public NameValue(String name, int value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
}
