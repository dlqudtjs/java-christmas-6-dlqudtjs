package christmas.View;

import static christmas.constant.CommonSymbol.COLON_SPACE;
import static christmas.constant.CommonSymbol.MENU_UNIT;
import static christmas.constant.CommonSymbol.PRICE_UNIT;
import static christmas.constant.CommonSymbol.SPACE;

import christmas.constant.PlannerMessage;
import christmas.model.Amount;
import christmas.model.enums.EventType;
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
        PlannerMessage message = PlannerMessage.OUTPUT_TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printTotalPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        System.out.println(totalPriceBeforeDiscount + PRICE_UNIT.getSymbol());
    }

    public static void printGiveawayTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_GIVEAWAY_MENU_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printGiveawayDetails(Map<Menu, Integer> giveaway) {
        giveaway.forEach((menu, count) ->
                System.out.println(menu.getName() + SPACE.getSymbol() + count + MENU_UNIT.getSymbol())
        );
    }

    public static void printBenefitDetailsTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_BENEFIT_DETAILS_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printBenefitDetails(Map<EventType, Amount> benefit) {
        benefit.forEach((eventType, discount) ->
                System.out.println(
                        eventType.getName() + COLON_SPACE.getSymbol() + discount.getValue() + PRICE_UNIT.getSymbol()
                )
        );
    }

    public static void printTotalBenefitAmountTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_TOTAL_BENEFIT_AMOUNT_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println(totalBenefitAmount + PRICE_UNIT.getSymbol());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printNewLine() {
        System.out.println();
    }
}
