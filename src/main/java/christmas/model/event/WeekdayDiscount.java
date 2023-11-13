package christmas.model.event;

import christmas.model.Amount;
import christmas.model.BookingInfo;
import christmas.model.enums.MenuType;

public class WeekdayDiscount implements PlannerEvent {

    private static final int WEEKDAY_DISCOUNT_PRICE = 2_023;
    private static final MenuType MENU_TYPE = MenuType.DESSERT;

    private final BookingInfo bookingInfo;

    public WeekdayDiscount(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public Amount getDiscount() {
        if (isWeekend()) {
            return new Amount(0);
        }

        return new Amount(getWeekdayDiscount());
    }

    private int getWeekdayDiscount() {
        return WEEKDAY_DISCOUNT_PRICE * getMenuCountByMenuType();
    }

    private int getMenuCountByMenuType() {
        return bookingInfo.getOrder().getMenuTypeCount(MENU_TYPE);
    }

    private boolean isWeekend() {
        return bookingInfo.getVisitDate().isWeekend();
    }
}
