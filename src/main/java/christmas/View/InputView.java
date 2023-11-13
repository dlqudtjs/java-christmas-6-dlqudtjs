package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.ExceptionMessage;
import christmas.constant.PlannerMessage;
import christmas.util.Parser;
import java.util.regex.Pattern;

public class InputView {

    // ex) 티본스테이크-1,아이스크림-2
    private static final String MENU_COUNT_FORMAT = "^[a-zA-Z가-힣]+-\\d+(?:,\\s?[a-zA-Z가-힣]+-\\d+)*$";

    private InputView() {
    }

    public static int readVisitDate() {
        PlannerMessage message = PlannerMessage.INPUT_VISIT_DATE_MESSAGE;
        System.out.println(message.getMessage());

        return Parser.parseToInt(Console.readLine());
    }

    public static String readMenuCount() {
        PlannerMessage message = PlannerMessage.INPUT_MENU_COUNT_MESSAGE;
        System.out.println(message.getMessage());

        return validateMenuCountFormat(Console.readLine());
    }

    private static String validateMenuCountFormat(String menuCount) {
        if (Pattern.matches(MENU_COUNT_FORMAT, menuCount)) {
            return menuCount;
        }

        ExceptionMessage message = ExceptionMessage.INVALID_MENU_COUNT_FORMAT;
        throw new IllegalArgumentException(message.getMessage());
    }
}
