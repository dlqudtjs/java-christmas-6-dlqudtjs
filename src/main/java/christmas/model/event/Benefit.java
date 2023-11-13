package christmas.model.event;

import static christmas.model.event.EventConfig.EVENT_ENTRY_MINIMUM;

import christmas.model.Amount;
import christmas.model.BookingInfo;
import christmas.model.Order;
import christmas.model.enums.EventType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Benefit {

    private final Map<EventType, Amount> eventDetails;

    public Benefit(BookingInfo bookingInfo) {
        this.eventDetails = createEventDetails(bookingInfo);
    }

    public Map<EventType, Amount> getEventDetails() {
        return Collections.unmodifiableMap(eventDetails);
    }

//    public Map<Menu, Integer> getGiveawayMenu() {
//        PlannerEvent giveaway = createEvent(EventType.GIFT_EVENT);
//        return ((Giveaway) giveaway).getMenus();
//    }

    public Amount getTotalBenefitAmount() {
        int totalBenefitPrice = eventDetails.values().stream()
                .mapToInt(Amount::getValue)
                .sum();

        return new Amount(totalBenefitPrice);
    }

    private Amount getEventDiscountPrice(EventType eventType, BookingInfo bookingInfo) {
        return createEvent(eventType, bookingInfo).getDiscount();
    }

    private Map<EventType, Amount> createEventDetails(BookingInfo bookingInfo) {
        if (!canParticipateInEvent(getTotalOrderPrice(bookingInfo))) {
            return Map.of(EventType.NONE, new Amount(0));
        }

        return getActiveEventTypes().stream()
                .filter(eventType -> getEventDiscountPrice(eventType, bookingInfo).getValue() > 0)
                .collect(Collectors.toMap(
                        eventType -> eventType,
                        eventType -> getEventDiscountPrice(eventType, bookingInfo)
                ));
    }

    private PlannerEvent createEvent(EventType eventType, BookingInfo bookingInfo) {
        PlannerEventFactory eventFactory = eventType.getEventFactory();
        return eventFactory.createEvent(bookingInfo);
    }

    private List<EventType> getActiveEventTypes() {
        return EventType.getActiveEventTypes();
    }

    private boolean canParticipateInEvent(Amount amount) {
        return amount.isGreaterThan(new Amount(EVENT_ENTRY_MINIMUM));
    }

    private Amount getTotalOrderPrice(BookingInfo bookingInfo) {
        return getOrder(bookingInfo).getTotalPrice();
    }

    private Order getOrder(BookingInfo bookingInfo) {
        return bookingInfo.getOrder();
    }

}
