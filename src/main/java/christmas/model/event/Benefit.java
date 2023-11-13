package christmas.model.event;

import static christmas.model.event.EventConfig.EVENT_ENTRY_MINIMUM;

import christmas.model.Amount;
import christmas.model.BookingInfo;
import christmas.model.enums.EventType;
import christmas.model.enums.Menu;
import java.util.List;
import java.util.Map;

public class Benefit {

    private final BookingInfo bookingInfo;

    public Benefit(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public Map<Menu, Integer> getGiveawayMenu() {
        PlannerEvent giveaway = createEvent(EventType.GIFT_EVENT);
        return ((Giveaway) giveaway).getMenus();
    }

    public Amount getTotalBenefitAmount() {
        if (canParticipateInEvent(bookingInfo.getOrder().getTotalPrice())) {
            return new Amount(getTotalBenefitPrice());
        }

        return new Amount(0);
    }

    private int getTotalBenefitPrice() {
        return getActiveEventTypes().stream()
                .map(this::createEvent)
                .map(PlannerEvent::getDiscount)
                .mapToInt(Amount::getValue)
                .sum();
    }

    private PlannerEvent createEvent(EventType eventType) {
        PlannerEventFactory eventFactory = eventType.getEventFactory();
        return eventFactory.createEvent(bookingInfo);
    }

    private List<EventType> getActiveEventTypes() {
        return EventType.getActiveEventTypes();
    }

    private boolean canParticipateInEvent(Amount amount) {
        return amount.isGreaterThan(new Amount(EVENT_ENTRY_MINIMUM));
    }
}
