package christmas.model;

public class Amount {

    private int value;

    public Amount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int discount(Amount amount) {
        return value - amount.getValue();
    }

    public boolean isGreaterThan(Amount amount) {
        return value > amount.getValue();
    }

    public void add(Amount amount) {
        value += amount.getValue();
    }
}
