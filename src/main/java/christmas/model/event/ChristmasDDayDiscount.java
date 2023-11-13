package christmas.model.event;

import christmas.model.Amount;
import christmas.model.BookingInfo;

public class ChristmasDDayDiscount implements PlannerEvent {

    private static final int INIT_PRICE = 1_000;
    private static final int DISCOUNT_PRICE = 100;

    private final BookingInfo bookingInfo;

    public ChristmasDDayDiscount(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public Amount getDiscount() {
        return new Amount(INIT_PRICE + (DISCOUNT_PRICE * getDay()));
    }

    private int getDay() {
        return bookingInfo.getVisitDate().getDay();
    }
}
