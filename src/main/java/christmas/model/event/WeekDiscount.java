package christmas.model.event;

import christmas.model.Amount;
import christmas.model.BookingInfo;
import christmas.model.enums.MenuType;

public class WeekDiscount implements Event {

    private static final int WEEKDAY_DISCOUNT_PRICE = 2_023;
    private static final int WEEKEND_DISCOUNT_PRICE = 2_023;

    private final BookingInfo bookingInfo;

    public WeekDiscount(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public Amount getDiscount() {
        if (isWeekend()) {
            return new Amount(getWeekendDiscount());
        }

        return new Amount(getWeekdayDiscount());
    }

    private int getWeekendDiscount() {
        return WEEKEND_DISCOUNT_PRICE * getMenuCountByMenuType(MenuType.MAIN_DISH);
    }

    private int getWeekdayDiscount() {
        return WEEKDAY_DISCOUNT_PRICE * getMenuCountByMenuType(MenuType.DESSERT);
    }

    private int getMenuCountByMenuType(MenuType menuType) {
        return bookingInfo.getOrder().getMenuTypeCount(menuType);
    }

    private boolean isWeekend() {
        return bookingInfo.getVisitDate().isWeekend();
    }
}
