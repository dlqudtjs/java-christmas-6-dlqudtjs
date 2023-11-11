package christmas.model.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MenuTest {

    @DisplayName("에피타이저 메뉴 테스트")
    @ParameterizedTest
    @CsvSource(value = {"MUSHROOM_SOUP", "TAPAS", "CAESAR_SALAD"})
    void isAppetizer(Menu menu) {
        // then
        Assertions.assertThat(menu.is(MenuType.APPETIZER)).isTrue();
    }

    @DisplayName("메인 메뉴 테스트")
    @ParameterizedTest
    @CsvSource(value = {"T_BONE_STEAK", "BBQ_RIBS", "SEAFOOD_PASTA", "CHRISTMAS_PASTA"})
    void isMainDish(Menu menu) {
        // then
        Assertions.assertThat(menu.is(MenuType.MAIN_DISH)).isTrue();
    }

    @DisplayName("음료 메뉴 테스트")
    @ParameterizedTest
    @CsvSource(value = {"ZERO_COKE", "RED_WINE", "CHAMPAGNE"})
    void isDrink(Menu menu) {
        // then
        Assertions.assertThat(menu.is(MenuType.DRINK)).isTrue();
    }

    @DisplayName("이름으로 메뉴 찾기 테스트 (가격, 이름 반환 테스트)")
    @ParameterizedTest
    @CsvSource(value = {"MUSHROOM_SOUP, 양송이수프, 6_000", "TAPAS, 타파스, 5_500", "CAESAR_SALAD, 시저샐러드, 8_000",
            "T_BONE_STEAK, 티본스테이크, 55_000", "BBQ_RIBS, 바비큐립, 54_000", "SEAFOOD_PASTA, 해산물파스타, 35_000",
            "CHRISTMAS_PASTA, 크리스마스파스타, 25_000", "CHOCOLATE_CAKE, 초코케이크, 15_000", "ICE_CREAM, 아이스크림, 5_000",
            "ZERO_COKE, 제로콜라, 3_000", "RED_WINE, 레드와인, 60_000", "CHAMPAGNE, 샴페인, 25_000"})
    void getNameAndPrice(Menu menu, String name, int price) {
        // when
        Menu menuWithName = Menu.getMenuWithName(name);

        // then
        Assertions.assertThat(menuWithName).isEqualTo(menu);
        Assertions.assertThat(menuWithName.getPrice()).isEqualTo(price);
        Assertions.assertThat(menuWithName.getName()).isEqualTo(name);
    }
}
