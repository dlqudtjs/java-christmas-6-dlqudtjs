package christmas.model.event;

import christmas.model.Amount;
import christmas.model.BookingInfo;

public class SpecialDiscount implements PlannerEvent {

    private static final int DISCOUNT_PRICE = 1_000;
    private final BookingInfo bookingInfo;

    public SpecialDiscount(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public Amount getDiscount() {
        if (isSpecialDay()) {
            return new Amount(DISCOUNT_PRICE);
        }

        return new Amount(0);
    }

    private boolean isSpecialDay() {
        return bookingInfo.getVisitDate().isSpecialDay();
    }
}
