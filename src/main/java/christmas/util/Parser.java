package christmas.util;

import christmas.constant.ExceptionMessage;

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
}
