package christmas.constant;

public enum PlannerConfig {

    PLANNER_YEAR(2023),
    MAX_TOTAL_MENU_COUNT(20),
    MIN_MENU_COUNT(1),
    DECEMBER(12);

    private final int value;

    PlannerConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
