package christmas.util;

import christmas.constant.CommonSymbol;
import christmas.constant.ExceptionMessage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        try {
            return List.of(value.split(symbol.getSymbol()));
        } catch (IllegalArgumentException e) {
            ExceptionMessage message = ExceptionMessage.INVALID_MENU_COUNT_FORMAT;
            throw new IllegalArgumentException(message.getMessage());
        }
    }

    public static Map<String, Integer> parseToMap(List<String> menuCounts, CommonSymbol symbol) {
        try {
            return menuCounts.stream()
                    .map(menuCount -> parseToList(menuCount, symbol))
                    .collect(Collectors.toMap(
                            menuCount -> menuCount.get(0),
                            menuCount -> parseToInt(menuCount.get(1))
                    ));
        } catch (IllegalArgumentException e) {
            ExceptionMessage message = ExceptionMessage.INVALID_MENU_COUNT_FORMAT;
            throw new IllegalArgumentException(message.getMessage());
        }
    }

    public static String parseToNumberFormat(int value) {
        return String.format("%,d", value);
    }
}
