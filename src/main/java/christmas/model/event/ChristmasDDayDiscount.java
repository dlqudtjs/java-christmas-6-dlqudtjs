package christmas.model.event;

import christmas.model.Amount;
import christmas.model.BookingInfo;

public class ChristmasDDayDiscount implements Event {

    private static final int DISCOUNT_PRICE = 1_000;

    private final BookingInfo bookingInfo;

    public ChristmasDDayDiscount(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public Amount getDiscount() {
        return new Amount(DISCOUNT_PRICE * getDay());
    }

    private int getDay() {
        return bookingInfo.getVisitDate().getDay();
    }
}
