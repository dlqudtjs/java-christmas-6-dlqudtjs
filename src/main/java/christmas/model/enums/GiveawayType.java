package christmas.model.enums;

import java.util.List;

public enum GiveawayType {

    DECEMBER_GIVEAWAY(List.of(Menu.CHAMPAGNE), 120_000, false);

    private final List<Menu> menus;
    private final int price;

    private final boolean isPerUint;

    GiveawayType(List<Menu> menus, int price, boolean isPerUint) {
        this.menus = menus;
        this.price = price;
        this.isPerUint = isPerUint;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public boolean isAvailable(int price) {
        return price >= this.price;
    }

    public int getQuantity(int price) {
        if (isPerUint) {
            return price / this.price;
        }

        return 1;
    }
}
