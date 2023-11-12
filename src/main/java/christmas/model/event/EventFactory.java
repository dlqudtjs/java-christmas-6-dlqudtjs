package christmas.model.event;

import christmas.model.BookingInfo;

public interface EventFactory {

    Event createEvent(BookingInfo bookingInfo);
}
