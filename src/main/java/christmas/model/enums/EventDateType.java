package christmas.model.enums;

import java.util.Arrays;
import java.util.List;

public enum EventDateType {

    SPECIAL_DAY(Arrays.asList(3, 10, 17, 24, 25, 31));

    private final List<Integer> dates;

    EventDateType(List<Integer> dates) {
        this.dates = dates;
    }

    public boolean is(int date) {
        return dates.contains(date);
    }
}
