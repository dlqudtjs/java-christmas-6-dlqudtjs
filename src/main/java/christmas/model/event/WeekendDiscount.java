package christmas.model.event;

import static christmas.model.event.EventConfig.WEEKEND_DISCOUNT_PRICE;
import static christmas.model.event.EventConfig.WEEKEND_EVENT_MENU_TYPE;

import christmas.model.Amount;
import christmas.model.BookingInfo;

public class WeekendDiscount implements PlannerEvent {

    private final BookingInfo bookingInfo;

    public WeekendDiscount(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public Amount getDiscount() {
        if (isWeekend()) {
            return new Amount(getWeekendDiscount());
        }

        return new Amount(0);
    }

    private int getWeekendDiscount() {
        return WEEKEND_DISCOUNT_PRICE * getMenuCountByMenuType();
    }

    private int getMenuCountByMenuType() {
        return bookingInfo.getOrder().getMenuTypeCount(WEEKEND_EVENT_MENU_TYPE);
    }

    private boolean isWeekend() {
        return bookingInfo.getVisitDate().isWeekend();
    }
}
