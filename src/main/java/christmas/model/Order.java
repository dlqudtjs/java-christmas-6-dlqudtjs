package christmas.model;

import christmas.constant.ExceptionMessage;
import christmas.constant.PlannerConfig;
import christmas.model.enums.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> menuCountMap;

    public Order(Map<String, Integer> tempMenuCountMap) {
        validate(tempMenuCountMap);
        this.menuCountMap = createMenuCountMap(tempMenuCountMap);
    }

    private Map<Menu, Integer> createMenuCountMap(Map<String, Integer> tempMenuCountMap) {
        Map<Menu, Integer> convertedMenuCountMap = new HashMap<>();

        tempMenuCountMap.forEach((menuName, menuCount) -> {
            Menu menu = Menu.getMenuWithName(menuName);
            convertedMenuCountMap.put(menu, menuCount);
        });

        return convertedMenuCountMap;
    }

    private void validate(Map<String, Integer> tempMenuCountMap) {
        List<String> menuNames = tempMenuCountMap.keySet().stream().toList();
        List<Integer> menuCounts = tempMenuCountMap.values().stream().toList();

        validateMenuName(menuNames);
        validateMenuCount(menuCounts);
        validateTotalMenuCount(menuCounts);
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
}
