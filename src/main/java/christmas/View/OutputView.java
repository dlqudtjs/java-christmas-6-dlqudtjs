package christmas.View;

import static christmas.constant.PlannerConfig.DECEMBER;

import christmas.constant.PlannerMessage;
import christmas.model.Amount;
import christmas.model.VisitDate;
import christmas.model.enums.BadgeType;
import christmas.model.enums.EventType;
import christmas.model.enums.Menu;
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
        PlannerMessage message = PlannerMessage.ORDER_DETAILS_MESSAGE;
        order.forEach((menu, count) ->
                System.out.println(message.getMessage(menu.getName(), count))
        );
    }

    public static void printTotalPriceBeforeDiscountTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printTotalPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        PlannerMessage message = PlannerMessage.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT_MESSAGE;
        System.out.println(message.getMessage(totalPriceBeforeDiscount));
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

        PlannerMessage message = PlannerMessage.GIVEAWAY_MENU_MESSAGE;

        giveaway.forEach((menu, count) ->
                System.out.println(message.getMessage(menu.getName(), count))
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

        PlannerMessage message = PlannerMessage.BENEFIT_DETAILS_MESSAGE;
        benefit.forEach((eventType, discount) ->
                System.out.println(message.getMessage(
                        eventType.getName(), convertSign(discount.getValue()
                        ))
                ));
    }

    public static void printTotalBenefitAmountTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_TOTAL_BENEFIT_AMOUNT_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        PlannerMessage message = PlannerMessage.TOTAL_BENEFIT_AMOUNT_MESSAGE;
        System.out.println(message.getMessage(convertSign(totalBenefitAmount)));
    }

    public static void printTotalExpectedPaymentAfterDiscountTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_TOTAL_EXPECTED_PAYMENT_AFTER_DISCOUNT_TITLE_MESSAGE;
        System.out.println(message.getMessage());
    }

    public static void printTotalExpectedPaymentAfterDiscount(Amount totalPrice, Amount discountPrice) {
        PlannerMessage message = PlannerMessage.TOTAL_EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE;
        System.out.println(message.getMessage(totalPrice.getValue() - discountPrice.getValue()));
    }

    public static void printEventBadgeTitle() {
        PlannerMessage message = PlannerMessage.OUTPUT_EVENT_BADGE_TITLE_MESSAGE;
        System.out.println(message.getMessage(DECEMBER.getValue()));
    }

    public static void printEventBadge(BadgeType badge) {
        PlannerMessage message = PlannerMessage.EVENT_BADGE_MESSAGE;
        System.out.println(message.getMessage(badge.getName()));
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

    private static int convertSign(int value) {
        return value * -1;
    }
}
