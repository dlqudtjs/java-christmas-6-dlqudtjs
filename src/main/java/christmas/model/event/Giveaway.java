package christmas.model.event;

import christmas.model.Amount;
import christmas.model.BookingInfo;
import christmas.model.enums.GiveawayType;
import christmas.model.enums.Menu;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Giveaway implements PlannerEvent {

    private final Map<Menu, Integer> menus;

    public Giveaway(BookingInfo bookingInfo) {
        // todo 리팩토링
        int amount = bookingInfo.getOrder().getTotalPrice().getValue();

        GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
        this.menus = getMenu(giveawayType, new Amount(amount));
    }

    public Map<Menu, Integer> getMenus() {
        return Collections.unmodifiableMap(menus);
    }

    public boolean isExist() {
        return !menus.containsKey(Menu.NONE);
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

    private Map<Menu, Integer> getMenu(GiveawayType giveawayType, Amount amount) {
        if (isAvailable(giveawayType, amount)) {
            return generateMenu(giveawayType, amount);
        }

        return new HashMap<>() {{
            put(Menu.NONE, 0);
        }};
    }

    private Map<Menu, Integer> generateMenu(GiveawayType giveawayType, Amount amount) {
        return giveawayType.getMenus().stream()
                .collect(Collectors.toMap(
                        menu -> menu,
                        menu -> giveawayType.getQuantity(amount.getValue())
                ));
    }

    private boolean isAvailable(GiveawayType giveawayType, Amount amount) {
        return giveawayType.isAvailable(amount.getValue());
    }
}
