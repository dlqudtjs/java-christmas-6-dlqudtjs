package christmas.model;

import christmas.constant.ExceptionMessage;
import christmas.constant.PlannerConfig;
import christmas.model.enums.Menu;
import christmas.model.enums.MenuType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {

    private final Map<Menu, Integer> menuCountMap;

    public Order(Map<String, Integer> tempMenuCountMap) {
        validate(tempMenuCountMap);
        this.menuCountMap = createMenuCountMap(tempMenuCountMap);
    }

    public Map<Menu, Integer> getMenuCountMap() {
        return Collections.unmodifiableMap(menuCountMap);
    }

    public Amount getTotalPrice() {
        return new Amount(calculateTotalPrice());
    }

    public int getMenuTypeCount(MenuType menuType) {
        return menuCountMap.entrySet().stream()
                .filter(entry -> entry.getKey().is(menuType))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    private Map<Menu, Integer> createMenuCountMap(Map<String, Integer> tempMenuCountMap) {
        return tempMenuCountMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> Menu.getMenuWithName(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    private void validate(Map<String, Integer> tempMenuCountMap) {
        List<String> menuNames = tempMenuCountMap.keySet().stream().toList();
        List<Integer> menuCounts = tempMenuCountMap.values().stream().toList();

        validateMenuName(menuNames);
        validateMenuCount(menuCounts);
        validateTotalMenuCount(menuCounts);
        validateMenuOnlyDrink(menuNames);
    }

    private void validateMenuOnlyDrink(List<String> menuNames) {
        menuNames.stream()
                .filter(menuName -> !Menu.getMenuWithName(menuName).is(MenuType.DRINK))
                .findFirst()
                .orElseThrow(() -> {
                    ExceptionMessage message = ExceptionMessage.INVALID_MENU_ONLY_DRINK;
                    return new IllegalArgumentException(message.getMessage());
                });
    }

    private void validateTotalMenuCount(List<Integer> menuCounts) {
        int maxMenuCount = PlannerConfig.MAX_TOTAL_MENU_COUNT.getValue();

        if (getTotalMenuCount(menuCounts) > maxMenuCount) {
            ExceptionMessage message = ExceptionMessage.INVALID_MENU_MAX_COUNT;
            throw new IllegalArgumentException(message.getMessage());
        }
    }

    private void validateMenuName(List<String> menuNames) {
        menuNames.forEach(menuName -> {
            if (Menu.getMenuWithName(menuName).equals(Menu.NONE)) {
                ExceptionMessage message = ExceptionMessage.INVALID_MENU_NAME;
                throw new IllegalArgumentException(message.getMessage());
            }
        });
    }

    private void validateMenuCount(List<Integer> menuCounts) {
        int minMenuCount = PlannerConfig.MIN_MENU_COUNT.getValue();

        menuCounts.forEach(menuCount -> {
            if (menuCount < minMenuCount) {
                ExceptionMessage message = ExceptionMessage.INVALID_MENU_COUNT;
                throw new IllegalArgumentException(message.getMessage());
            }
        });
    }

    private int getTotalMenuCount(List<Integer> menuCounts) {
        return menuCounts.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int calculateTotalPrice() {
        return menuCountMap.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
