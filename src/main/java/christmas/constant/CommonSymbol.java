package christmas.constant;

public enum CommonSymbol {

    COMMA(","),
    DASH("-");

    private final String symbol;

    CommonSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
