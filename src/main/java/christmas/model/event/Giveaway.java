package christmas.model.event;

import static christmas.constant.PlannerConfig.NONE;

import christmas.model.Amount;
import christmas.model.BookingInfo;
import christmas.model.Order;
import christmas.model.enums.GiveawayType;
import christmas.model.enums.Menu;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Giveaway implements PlannerEvent {

    GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
    private final Map<Menu, Integer> menus;

    public Giveaway(BookingInfo bookingInfo) {
        this.menus = generateGiveaway(bookingInfo);
    }

    public Map<Menu, Integer> getMenus() {
        return Collections.unmodifiableMap(menus);
    }

    @Override
    public Amount getDiscount() {
        return new Amount(getTotalPrice());
    }

    private int getTotalPrice() {
        return menus.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private Map<Menu, Integer> generateGiveaway(BookingInfo bookingInfo) {
        if (!canProvideGiveaway(bookingInfo)) {
            return Map.of(Menu.NONE, NONE.getValue());
        }

        return giveawayType.getMenus().stream()
                .collect(Collectors.toMap(
                        menu -> menu,
                        menu -> giveawayType.getQuantity(getTotalOrderPrice(bookingInfo).getValue())
                ));
    }

    private boolean canProvideGiveaway(BookingInfo bookingInfo) {
        return giveawayType.isAvailable(getTotalOrderPrice(bookingInfo).getValue());
    }

    private Order getOrder(BookingInfo bookingInfo) {
        return bookingInfo.getOrder();
    }

    private Amount getTotalOrderPrice(BookingInfo bookingInfo) {
        return getOrder(bookingInfo).getTotalPrice();
    }
}
