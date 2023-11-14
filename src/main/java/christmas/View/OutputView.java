package christmas.View;

import static christmas.constant.CommonSymbol.COLON_SPACE;
import static christmas.constant.CommonSymbol.MENU_UNIT;
import static christmas.constant.CommonSymbol.MINUS;
import static christmas.constant.CommonSymbol.PRICE_UNIT;
import static christmas.constant.CommonSymbol.SPACE;
import static christmas.constant.PlannerConfig.DECEMBER;

import christmas.constant.PlannerMessage;
import christmas.model.Amount;
import christmas.model.VisitDate;
import christmas.model.enums.BadgeType;
import christmas.model.enums.EventType;
import christmas.model.enums.Menu;
import christmas.util.Parser;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printPlannerStartMessage() {
        PlannerMessage message = PlannerMessage.PLANNER_START_MESSAGE;
        System.out.println(message.getMessage(DECEMBER.getValue()));
    }

    public static void printEventBenefitPreviewMessage(VisitDate visitDate) {
        PlannerMessage message = PlannerMessage.EVENT_BENEFIT_PREVIEW_MESSAGE;
        System.out.println(message.getMessage(DECEMBER.getValue(), visitDate.getDay()));
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
        String parsedNumber = Parser.parseToNumberFormat(totalPriceBeforeDiscount);
        System.out.println(parsedNumber + PRICE_UNIT.getSymbol());
    }

    public static void printGiveawayTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_GIVEAWAY_MENU_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printGiveawayDetails(Map<Menu, Integer> giveaway) {
        if (!isExistGiveaway(giveaway)) {
            PlannerMessage message = PlannerMessage.OUTPUT_NO_DETAIL_MESSAGE;
            System.out.println(message.getMessage());
            return;
        }

        giveaway.forEach((menu, count) ->
                System.out.println(menu.getName() + SPACE.getSymbol() + count + MENU_UNIT.getSymbol())
        );
    }

    public static void printBenefitDetailsTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_BENEFIT_DETAILS_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printBenefitDetails(Map<EventType, Amount> benefit) {
        if (!isExistBenefit(benefit)) {
            PlannerMessage message = PlannerMessage.OUTPUT_NO_DETAIL_MESSAGE;
            System.out.println(message.getMessage());
            return;
        }

        benefit.forEach((eventType, discount) ->
                System.out.println(
                        eventType.getName() + COLON_SPACE.getSymbol()
                                + MINUS.getSymbol() + Parser.parseToNumberFormat(discount.getValue())
                                + PRICE_UNIT.getSymbol()
                )
        );
    }

    public static void printTotalBenefitAmountTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_TOTAL_BENEFIT_AMOUNT_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        String parsedNumber = Parser.parseToNumberFormat(totalBenefitAmount);
        System.out.println(MINUS.getSymbol() + parsedNumber + PRICE_UNIT.getSymbol());
    }

    public static void printTotalExpectedPaymentAfterDiscountTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_TOTAL_EXPECTED_PAYMENT_AFTER_DISCOUNT_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printTotalExpectedPaymentAfterDiscount(Amount totalPrice, Amount discountPrice) {
        String parsedNumber = Parser.parseToNumberFormat(totalPrice.getValue() - discountPrice.getValue());
        System.out.println(parsedNumber + PRICE_UNIT.getSymbol());
    }

    public static void printEventBadgeTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_EVENT_BADGE_TITLE_MESSAGE;
        System.out.println(message.getMessage(DECEMBER.getValue()));
    }

    public static void printEventBadge(BadgeType badge) {
        System.out.println(badge.getName());
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printNewLine() {
        System.out.println();
    }

    private static boolean isExistGiveaway(Map<Menu, Integer> giveaway) {
        return !giveaway.containsKey(Menu.NONE);
    }

    private static boolean isExistBenefit(Map<EventType, Amount> benefit) {
        return !benefit.containsKey(EventType.NONE);
    }
}
