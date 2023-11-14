package christmas.View;

import christmas.constant.CommonSymbol;
import christmas.constant.PlannerMessage;
import christmas.model.enums.Menu;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printPlannerStartMessage() {
        PlannerMessage message = PlannerMessage.PLANNER_START_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printOrderDetailsTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_ORDER_DETAILS_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printOrderDetails(Map<Menu, Integer> order) {
        order.forEach((menu, count) ->
                System.out.println(menu.getName() + CommonSymbol.SPACE + count + CommonSymbol.MENU_UNIT)
        );
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

}
