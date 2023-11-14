package christmas.model.enums;

import christmas.model.event.ChristmasDDayDiscount;
import christmas.model.event.Giveaway;
import christmas.model.event.PlannerEventFactory;
import christmas.model.event.SpecialDiscount;
import christmas.model.event.WeekdayDiscount;
import christmas.model.event.WeekendDiscount;
import java.util.Arrays;
import java.util.List;

public enum EventType {

    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", true, 10_000) {
        @Override
        public PlannerEventFactory getEventFactory() {
            return ChristmasDDayDiscount::new;
        }
    },
    WEEKDAY_DISCOUNT("평일 할인", true, 10_000) {
        @Override
        public PlannerEventFactory getEventFactory() {
            return WeekdayDiscount::new;
        }
    },
    WEEKEND_DISCOUNT("주말 할인", true, 10_000) {
        @Override
        public PlannerEventFactory getEventFactory() {
            return WeekendDiscount::new;
        }
    },
    SPECIAL_DISCOUNT("특별 할인", true, 10_000) {
        @Override
        public PlannerEventFactory getEventFactory() {
            return SpecialDiscount::new;
        }
    },
    GIFT_EVENT("증정 이벤트", true, 120_000) {
        @Override
        public PlannerEventFactory getEventFactory() {
            return Giveaway::new;
        }
    },
    NONE("없음", false, 0) {
        @Override
        public PlannerEventFactory getEventFactory() {
            return null;
        }
    };

    private final String name;
    private final boolean active;
    private final int minimumPrice;

    EventType(String name, boolean active, int minimumPrice) {
        this.name = name;
        this.active = active;
        this.minimumPrice = minimumPrice;
    }

    public String getName() {
        return name;
    }

    public static List<EventType> getActiveEventTypes() {
        return Arrays.stream(values())
                .filter(EventType::isActive)
                .toList();
    }

    public boolean canParticipateInEvent(int price) {
        return price >= minimumPrice;
    }

    public boolean isActive() {
        return active;
    }

    public abstract PlannerEventFactory getEventFactory();
}
