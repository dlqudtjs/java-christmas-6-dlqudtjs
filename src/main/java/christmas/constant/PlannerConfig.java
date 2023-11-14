package christmas.constant;

public enum PlannerConfig {

    PLANNER_YEAR(2023),
    DECEMBER(12),
    MAX_TOTAL_MENU_COUNT(20),
    MIN_MENU_COUNT(1),
    NONE(0);

    private final int value;

    PlannerConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
