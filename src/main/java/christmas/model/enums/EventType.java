package christmas.model.enums;

import java.util.Arrays;
import java.util.List;

public enum EventType {

    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", true),
    WEEKDAY_DISCOUNT("평일 할인", true),
    WEEKEND_DISCOUNT("주말 할인", true),
    GIFT_EVENT("증정 이벤트", true);

    private final String name;
    private final boolean active;

    EventType(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public static List<EventType> getActiveEventTypes() {
        return Arrays.stream(values())
                .filter(EventType::isActive)
                .toList();
    }

    public boolean isActive() {
        return active;
    }
}