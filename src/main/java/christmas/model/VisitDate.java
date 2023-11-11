package christmas.model;

import christmas.constant.ExceptionMessage;
import christmas.constant.PlannerConfig;
import java.time.LocalDate;
import java.time.YearMonth;

public class VisitDate {

    private final LocalDate date;

    public VisitDate(int day) {
        validate(day);
        this.date = settingDate(day);
    }

    private LocalDate settingDate(int day) {
        return LocalDate.of(
                PlannerConfig.PLANNER_YEAR.getValue(),
                PlannerConfig.DECEMBER.getValue(),
                day);
    }

    private void validate(int day) {
        validateDay(day);
    }

    private void validateDay(int day) {
        YearMonth yearMonth = YearMonth.of(
                PlannerConfig.PLANNER_YEAR.getValue(),
                PlannerConfig.DECEMBER.getValue());

        if (day < 1 || day > yearMonth.lengthOfMonth()) {
            ExceptionMessage message = ExceptionMessage.INVALID_VISIT_DATE;
            throw new IllegalArgumentException(message.getMessage());
        }
    }
}
