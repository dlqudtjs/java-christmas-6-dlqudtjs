package christmas.controller;

import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.model.VisitDate;

public class PlannerController {

    public void run() {
        VisitDate visitDate = createVisitDate();
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

    private int readVisitDate() {
        return InputView.readVisitDate();
    }
}
