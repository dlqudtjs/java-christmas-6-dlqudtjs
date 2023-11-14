package christmas.controller;

import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.constant.CommonSymbol;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.util.Parser;
import java.util.List;
import java.util.Map;

public class PlannerController {

    public void run() {
        OutputView.printPlannerStartMessage();

        VisitDate visitDate = createVisitDate();
        Order order = new Order(createOrder());

        displayOrderDetails(order);
    }

    private void displayOrderDetails(Order order) {
        OutputView.printOrderDetailsTitle();
        OutputView.printOrderDetails(order.getMenuCountMap());
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
