package christmas.model;

import christmas.model.enums.Menu;
import christmas.model.event.Giveaway;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GiveawayTest {

    @DisplayName("증정품 총 가격 반환 테스트[샴페인 1개]")
    @ParameterizedTest
    @MethodSource("provideOrderTotalPriceGreaterThan120_000")
    void getTotalPrice(BookingInfo bookingInfo, int expectedPrice) {
        // given & when
        Giveaway giveaway = new Giveaway(bookingInfo);
        int totalPrice = giveaway.getDiscount().getValue();

        // then
        Assertions.assertThat(totalPrice).isEqualTo(expectedPrice);
    }

    @DisplayName("증정품 총 가격 반환 테스트[없음]")
    @ParameterizedTest
    @MethodSource("provideOrderTotalPriceLessThan120_000")
    void getTotalPriceWhenNoGiveaway(BookingInfo bookingInfo, int expectedPrice) {
        // given & when
        Giveaway giveaway = new Giveaway(bookingInfo);
        int totalPrice = giveaway.getDiscount().getValue();

        // then
        Assertions.assertThat(totalPrice).isEqualTo(expectedPrice);
    }

    @DisplayName("증정품 리스트 반환 테스트[샴페인 1개]")
    @ParameterizedTest
    @MethodSource("provideOrderTotalPriceGreaterThan120_000")
    void getMenus(BookingInfo bookingInfo) {
        // given & when
        Giveaway giveaway = new Giveaway(bookingInfo);
        Map<Menu, Integer> menus = giveaway.getMenus();

        // then
        Assertions.assertThat(menus).containsOnlyKeys(Menu.CHAMPAGNE);
        Assertions.assertThat(menus.get(Menu.CHAMPAGNE)).isEqualTo(1);
    }

    @DisplayName("증정품 리스트 반환 테스트[없으면 NONE 반환]")
    @ParameterizedTest
    @MethodSource("provideOrderTotalPriceLessThan120_000")
    void getMenusWhenNoGiveaway(BookingInfo bookingInfo) {
        // given & when
        Giveaway giveaway = new Giveaway(bookingInfo);
        Map<Menu, Integer> menus = giveaway.getMenus();

        // then
        Assertions.assertThat(menus).containsOnlyKeys(Menu.NONE);
        Assertions.assertThat(menus.get(Menu.NONE)).isEqualTo(0);
    }

    private static Stream<Arguments> provideOrderTotalPriceGreaterThan120_000() {
        return Stream.of(
                Arguments.of(new BookingInfo(new Order(Map.of("티본스테이크", 10)), new VisitDate(25)),
                        Menu.CHAMPAGNE.getPrice()),
                Arguments.of(new BookingInfo(new Order(Map.of("초코케이크", 8)), new VisitDate(26)),
                        Menu.CHAMPAGNE.getPrice())
        );
    }

    private static Stream<Arguments> provideOrderTotalPriceLessThan120_000() {
        return Stream.of(
                Arguments.of(new BookingInfo(new Order(Map.of("티본스테이크", 1)), new VisitDate(25)),
                        Menu.NONE.getPrice()),
                Arguments.of(new BookingInfo(new Order(Map.of("바비큐립", 1, "티본스테이크", 1)), new VisitDate(26)),
                        Menu.NONE.getPrice())
        );
    }
}

