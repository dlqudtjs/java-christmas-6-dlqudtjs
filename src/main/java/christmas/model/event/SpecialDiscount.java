package christmas.model.event;

import static christmas.model.event.EventConfig.SPECIAL_DISCOUNT_PRICE;

import christmas.model.Amount;
import christmas.model.BookingInfo;

public class SpecialDiscount implements PlannerEvent {

    private final BookingInfo bookingInfo;

    public SpecialDiscount(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public Amount getDiscount() {
        if (isSpecialDay()) {
            return new Amount(SPECIAL_DISCOUNT_PRICE);
        }

        return new Amount(0);
    }

    private boolean isSpecialDay() {
        return bookingInfo.getVisitDate().isSpecialDay();
    }
}
