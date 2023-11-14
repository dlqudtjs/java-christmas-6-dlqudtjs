package christmas.constant;

public enum CommonSymbol {

    COMMA(","),
    DASH("-"),
    SPACE(" "),
    MENU_UNIT("개"),
    PRICE_UNIT("원");

    private final String symbol;

    CommonSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
