package christmas.model;

import christmas.constant.ExceptionMessage;
import christmas.constant.PlannerConfig;
import christmas.model.enums.EventDateType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public class VisitDate {

    private final LocalDate date;

    public VisitDate(int day) {
        validate(day);
        this.date = settingDate(day);
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay() {
        return EventDateType.SPECIAL_DAY.is(getDay());
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
