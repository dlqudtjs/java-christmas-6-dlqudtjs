package christmas.model.event;

import christmas.model.Amount;
import christmas.model.BookingInfo;
import christmas.model.enums.MenuType;

public class WeekendDiscount implements PlannerEvent {

    private static final int WEEKEND_DISCOUNT_PRICE = 2_023;

    private static final MenuType MENU_TYPE = MenuType.MAIN_DISH;

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
        return bookingInfo.getOrder().getMenuTypeCount(MENU_TYPE);
    }

    private boolean isWeekend() {
        return bookingInfo.getVisitDate().isWeekend();
    }
}
