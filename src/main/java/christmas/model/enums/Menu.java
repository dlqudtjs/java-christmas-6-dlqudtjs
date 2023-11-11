package christmas.model.enums;

public enum Menu {

    // <에피타이저>
    MUSHROOM_SOUP("양송이수프", 6_000, MenuType.APPETIZER),
    TAPAS("타파스", 5_500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuType.APPETIZER),

    // <메인>
    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN_DISH),
    BBQ_RIBS("바비큐립", 54_000, MenuType.MAIN_DISH),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN_DISH),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN_DISH),

    // <디저트>
    CHOCOLATE_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),

    // <음료>
    ZERO_COKE("제로콜라", 3_000, MenuType.DRINK),
    RED_WINE("레드와인", 60_000, MenuType.DRINK),
    CHAMPAGNE("샴페인", 25_000, MenuType.DRINK);

    private final String name;
    private final int price;
    private final MenuType menuType;

    Menu(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }
}
