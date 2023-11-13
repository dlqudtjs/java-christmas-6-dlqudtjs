package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.PlannerMessage;
import christmas.util.Parser;

public class InputView {

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

        return Console.readLine();
    }
}
