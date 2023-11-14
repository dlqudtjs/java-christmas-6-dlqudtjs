package christmas.model.event;

import static christmas.constant.PlannerConfig.NONE;
import static christmas.model.event.EventConfig.WEEKDAY_DISCOUNT_PRICE;
import static christmas.model.event.EventConfig.WEEKDAY_EVENT_MENU_TYPE;

import christmas.model.Amount;
import christmas.model.BookingInfo;

public class WeekdayDiscount implements PlannerEvent {


    private final BookingInfo bookingInfo;

    public WeekdayDiscount(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public Amount getDiscount() {
        if (isWeekend()) {
            return new Amount(NONE.getValue());
        }

        return new Amount(getWeekdayDiscount());
    }

    private int getWeekdayDiscount() {
        return WEEKDAY_DISCOUNT_PRICE * getMenuCountByMenuType();
    }

    private int getMenuCountByMenuType() {
        return bookingInfo.getOrder().getMenuTypeCount(WEEKDAY_EVENT_MENU_TYPE);
    }

    private boolean isWeekend() {
        return bookingInfo.getVisitDate().isWeekend();
    }
}
