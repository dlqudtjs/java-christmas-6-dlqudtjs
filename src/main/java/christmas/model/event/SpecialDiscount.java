package christmas.model.event;

import static christmas.constant.PlannerConfig.NONE;
import static christmas.model.enums.EventDateType.SPECIAL_DAY;
import static christmas.model.event.EventConfig.SPECIAL_DISCOUNT_PRICE;

import christmas.model.Amount;
import christmas.model.BookingInfo;
import christmas.model.VisitDate;

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

        return new Amount(NONE.getValue());
    }

    private boolean isSpecialDay() {
        return SPECIAL_DAY.is(getDay());
    }

    private int getDay() {
        return getVisitDateFromBookingInfo().getDay();
    }

    private VisitDate getVisitDateFromBookingInfo() {
        return bookingInfo.getVisitDate();
    }
}
