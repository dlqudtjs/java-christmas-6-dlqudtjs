package christmas.model.event;

import christmas.model.BookingInfo;

public interface PlannerEventFactory {

    PlannerEvent createEvent(BookingInfo bookingInfo);
}
