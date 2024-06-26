package christmas.model;

public class Amount {

    private int value;

    public Amount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void discount(Amount amount) {
        value -= amount.getValue();
    }

    public void add(Amount amount) {
        value += amount.getValue();
    }
}
