package christmas.model;

import christmas.constant.ExceptionMessage;
import christmas.constant.PlannerConfig;
import christmas.model.enums.Menu;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> menuCountMap;

    public Order(Map<String, Integer> tempMenuCountMap) {
        validate(tempMenuCountMap);
    }

    private void validate(Map<String, Integer> tempMenuCountMap) {
        tempMenuCountMap.forEach((menuName, menuCount) -> {
            validateMenuName(menuName);
            validateMenuCount(menuCount);
        });
    }

    private void validateMenuName(String menuName) {
        if (Menu.getMenuWithName(menuName).equals(Menu.NONE)) {
            ExceptionMessage message = ExceptionMessage.INVALID_MENU_NAME;
            throw new IllegalArgumentException(message.getMessage());
        }
    }

    private void validateMenuCount(int menuCount) {
        int maxMenuCount = PlannerConfig.MAX_MENU_COUNT.getValue();
        int minMenuCount = PlannerConfig.MIN_MENU_COUNT.getValue();

        if (menuCount < minMenuCount || menuCount > maxMenuCount) {
            ExceptionMessage message = ExceptionMessage.INVALID_MENU_COUNT;
            throw new IllegalArgumentException(message.getMessage());
        }
    }
}
