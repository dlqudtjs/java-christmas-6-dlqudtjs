package christmas.model;

import christmas.constant.ExceptionMessage;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderTest {

    @DisplayName("메뉴판에 없는 메뉴를 주문하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideOrderMenuNotInMenuList")
    void orderMenuNotInMenuList(Map<String, Integer> menuCountMap) {
        Assertions.assertThatThrownBy(() -> new Order(menuCountMap))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_MENU_NAME.getMessage());
    }

    @DisplayName("메뉴판에 있는 메뉴를 주문하면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("provideOrderMenuInMenuList")
    void orderMenuInMenuList(Map<String, Integer> menuCountMap) {
        Assertions.assertThatCode(() -> new Order(menuCountMap))
                .doesNotThrowAnyException();
    }

    @DisplayName("주문한 메뉴의 개수가 0개 이하이면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideOrderMenuCountLessThanOrEqualToZero")
    void orderMenuCountLessThanOrEqualToZero(Map<String, Integer> menuCountMap) {
        Assertions.assertThatThrownBy(() -> new Order(menuCountMap))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_MENU_COUNT.getMessage());
    }

    @DisplayName("주문한 메뉴의 개수가 0개 초과이면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("provideOrderMenuCountGreaterThanZero")
    void orderMenuCountGreaterThanZero(Map<String, Integer> menuCountMap) {
        Assertions.assertThatCode(() -> new Order(menuCountMap))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> provideOrderMenuNotInMenuList() {
        return Stream.of(
                Arguments.of(Map.of("불고기", 1)),
                Arguments.of(Map.of("돼지국밥", 1, "피자", 1)),
                Arguments.of(Map.of("햄버거", 1, "사이다", 1, "치킨", 1))
        );
    }

    private static Stream<Arguments> provideOrderMenuInMenuList() {
        return Stream.of(
                Arguments.of(Map.of("티본스테이크", 1)),
                Arguments.of(Map.of("제로콜라", 1, "양송이수프", 1)),
                Arguments.of(Map.of("레드와인", 1, "크리스마스파스타", 1, "초코케이크", 1))
        );
    }

    private static Stream<Arguments> provideOrderMenuCountLessThanOrEqualToZero() {
        return Stream.of(
                Arguments.of(Map.of("티본스테이크", 0)),
                Arguments.of(Map.of("제로콜라", 1, "양송이수프", 0)),
                Arguments.of(Map.of("레드와인", 1, "크리스마스파스타", 1, "초코케이크", 0)),
                Arguments.of(Map.of("레드와인", 1, "크리스마스파스타", 1, "초코케이크", -1))
        );
    }

    private static Stream<Arguments> provideOrderMenuCountGreaterThanZero() {
        return Stream.of(
                Arguments.of(Map.of("티본스테이크", 1)),
                Arguments.of(Map.of("제로콜라", 10, "양송이수프", 5)),
                Arguments.of(Map.of("레드와인", 12, "크리스마스파스타", 3, "초코케이크", 1))
        );
    }
}
