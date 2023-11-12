package christmas.model;

public class Amount {

    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Amount discount(Amount amount) {
        return new Amount(value - amount.getValue());
    }

    public boolean isGreaterThan(Amount amount) {
        return value > amount.getValue();
    }
}
