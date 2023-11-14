package christmas.model.event;

import christmas.model.enums.MenuType;

public class EventConfig {

    public static final int INIT_PRICE = 1_000;
    public static final int CHRISTMAS_D_DAY_DISCOUNT_PRICE = 100;
    public static final int LAST_EVENT_DAY = 25;
    public static final int START_DAY_ADJUSTMENT = 100;
    public static final int SPECIAL_DISCOUNT_PRICE = 1_000;
    public static final int WEEKDAY_DISCOUNT_PRICE = 2_023;
    public static final int WEEKEND_DISCOUNT_PRICE = 2_023;
    public static final MenuType WEEKDAY_EVENT_MENU_TYPE = MenuType.DESSERT;

    public static final MenuType WEEKEND_EVENT_MENU_TYPE = MenuType.MAIN_DISH;

    private EventConfig() {
    }
}
