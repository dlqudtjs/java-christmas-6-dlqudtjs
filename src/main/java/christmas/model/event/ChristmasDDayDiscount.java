package christmas.model.event;

import static christmas.constant.PlannerConfig.NONE;
import static christmas.model.event.EventConfig.CHRISTMAS_D_DAY_DISCOUNT_PRICE;
import static christmas.model.event.EventConfig.INIT_PRICE;
import static christmas.model.event.EventConfig.LAST_EVENT_DAY;
import static christmas.model.event.EventConfig.START_DAY_ADJUSTMENT;

import christmas.model.Amount;
import christmas.model.BookingInfo;

public class ChristmasDDayDiscount implements PlannerEvent {

    private final BookingInfo bookingInfo;

    public ChristmasDDayDiscount(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public Amount getDiscount() {
        if (isPastLastEventDay()) {
            return new Amount(NONE.getValue());
        }

        return new Amount(calculateDiscountPrice());
    }

    private boolean isPastLastEventDay() {
        return getDay() > LAST_EVENT_DAY;
    }

    private int getDay() {
        return bookingInfo.getVisitDate().getDay();
    }

    private int calculateDiscountPrice() {
        return INIT_PRICE + (CHRISTMAS_D_DAY_DISCOUNT_PRICE * getDay()) - START_DAY_ADJUSTMENT;
    }
}
