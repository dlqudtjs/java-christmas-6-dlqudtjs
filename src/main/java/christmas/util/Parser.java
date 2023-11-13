package christmas.util;

import christmas.constant.CommonSymbol;
import christmas.constant.ExceptionMessage;
import java.util.List;

public class Parser {

    private Parser() {
    }

    public static int parseToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            ExceptionMessage message = ExceptionMessage.INVALID_VISIT_DATE;
            throw new IllegalArgumentException(message.getMessage());
        }
    }

    public static List<String> parseToList(String value, CommonSymbol symbol) {
        return List.of(value.split(symbol.getSymbol()));
    }
}
