package christmas.model.enums;

import java.util.Arrays;

public enum BadgeType {

    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String name;
    private final int price;

    BadgeType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public static BadgeType getBadgeWithPrice(int price) {
        return Arrays.stream(values())
                .filter(badge -> badge.isPriceLessThanOrEqualTo(price))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isPriceLessThanOrEqualTo(int price) {
        return this.price <= price;
    }
}
