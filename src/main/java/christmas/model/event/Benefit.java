package christmas.model.event;

import static christmas.constant.PlannerConfig.NONE;

import christmas.model.Amount;
import christmas.model.BookingInfo;
import christmas.model.Order;
import christmas.model.enums.BadgeType;
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

    public Amount getTotalBenefitAmount() {
        int totalBenefitPrice = eventDetails.values().stream()
                .mapToInt(Amount::getValue)
                .sum();

        return new Amount(totalBenefitPrice);
    }

    public BadgeType getBadge() {
        return BadgeType.getBadgeWithPrice(getTotalBenefitAmount().getValue());
    }

    private Amount getEventDiscountPrice(EventType eventType, BookingInfo bookingInfo) {
        return createEvent(eventType, bookingInfo).getDiscount();
    }

    private Map<EventType, Amount> createEventDetails(BookingInfo bookingInfo) {
        Map<EventType, Amount> eventDetails = generateEventDetails(bookingInfo);
        if (eventDetails.size() != NONE.getValue()) {
            return eventDetails;
        }

        return Map.of(EventType.NONE, new Amount(NONE.getValue()));
    }

    private Map<EventType, Amount> generateEventDetails(BookingInfo bookingInfo) {
        return getActiveEventTypes().stream()
                .filter(eventType -> canParticipateInEvent(eventType, getTotalOrderPrice(bookingInfo)))
                .filter(eventType -> createEvent(eventType, bookingInfo).getDiscount().getValue() > NONE.getValue())
                .filter(eventType -> eventType != EventType.NONE)
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

    private Amount getTotalOrderPrice(BookingInfo bookingInfo) {
        return getOrder(bookingInfo).getTotalPrice();
    }

    private Order getOrder(BookingInfo bookingInfo) {
        return bookingInfo.getOrder();
    }

    private boolean canParticipateInEvent(EventType eventType, Amount amount) {
        return eventType.canParticipateInEvent(amount.getValue());
    }
}
