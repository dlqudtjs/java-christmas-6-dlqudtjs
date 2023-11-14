package christmas.View;

import static christmas.constant.CommonSymbol.MENU_UNIT;
import static christmas.constant.CommonSymbol.PRICE_UNIT;
import static christmas.constant.CommonSymbol.SPACE;

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

    public static void printEventBenefitPreviewMessage() {
        PlannerMessage message = PlannerMessage.EVENT_BENEFIT_PREVIEW_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printOrderDetailsTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_ORDER_DETAILS_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printOrderDetails(Map<Menu, Integer> order) {
        order.forEach((menu, count) ->
                System.out.println(menu.getName() + SPACE.getSymbol() + count + MENU_UNIT.getSymbol())
        );
    }

    public static void printTotalPriceBeforeDiscountTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_TOTAL_ORDER_PRICE_BEFORE_DISCOUNT_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printTotalPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        System.out.println(totalPriceBeforeDiscount + PRICE_UNIT.getSymbol());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printNewLine() {
        System.out.println();
    }
}
