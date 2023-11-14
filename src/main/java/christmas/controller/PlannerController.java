package christmas.controller;

import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.constant.CommonSymbol;
import christmas.model.BookingInfo;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.event.Giveaway;
import christmas.util.Parser;
import java.util.List;
import java.util.Map;

public class PlannerController {

    public void run() {
        displayPlannerStartMessage();

        VisitDate visitDate = createVisitDate();
        Order order = new Order(createOrder());
        BookingInfo bookingInfo = new BookingInfo(order, visitDate);
        Giveaway giveaway = new Giveaway(bookingInfo);

        displayEventBenefitPreviewMessage();
        // <주문 메뉴>
        displayOrderDetails(order);
        // <할인 전 총주문 금액>
        displayTotalPriceBeforeDiscount(order);
        // <증정 메뉴>
        displayGiveawayDetails(giveaway);
    }

    private void displayPlannerStartMessage() {
        OutputView.printPlannerStartMessage();
    }

    private void displayEventBenefitPreviewMessage() {
        OutputView.printEventBenefitPreviewMessage();
        OutputView.printNewLine();
    }

    private void displayOrderDetails(Order order) {
        OutputView.printOrderDetailsTitle();
        OutputView.printOrderDetails(order.getMenuCountMap());
        OutputView.printNewLine();
    }

    private void displayTotalPriceBeforeDiscount(Order order) {
        OutputView.printTotalPriceBeforeDiscountTitle();
        OutputView.printTotalPriceBeforeDiscount(order.getTotalPrice().getValue());
        OutputView.printNewLine();
    }

    private void displayGiveawayDetails(Giveaway giveaway) {
        OutputView.printGiveawayTitle();
        OutputView.printGiveawayDetails(giveaway.getMenus());
        OutputView.printNewLine();
    }

    private VisitDate createVisitDate() {
        do {
            try {
                return new VisitDate(readVisitDate());
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e.getMessage());
            }
        } while (true);
    }

    private Map<String, Integer> createOrder() {
        do {
            try {
                List<String> menuCounts = parseMenuCount(readMenuCount(), CommonSymbol.COMMA);
                return Parser.parseToMap(menuCounts, CommonSymbol.DASH);
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e.getMessage());
            }
        } while (true);
    }

    private String readMenuCount() {
        return InputView.readMenuCount();
    }

    private List<String> parseMenuCount(String menuCount, CommonSymbol symbol) {
        return Parser.parseToList(menuCount, CommonSymbol.COMMA);
    }

    private int readVisitDate() {
        return InputView.readVisitDate();
    }
}
