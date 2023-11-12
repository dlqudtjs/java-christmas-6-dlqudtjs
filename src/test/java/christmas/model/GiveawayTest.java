package christmas.model;

import christmas.model.enums.GiveawayType;
import christmas.model.enums.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GiveawayTest {

    @DisplayName("[DECEMBER 이벤트]증정품 여부 반환 테스트[false]")
    @Test
    void isExistFalse() {
        // given
        GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
        Amount amount = new Amount(100_000);

        // when
        Giveaway giveaway = new Giveaway(giveawayType, amount);

        // then
        Assertions.assertThat(giveaway.isExist()).isFalse();
    }

    @DisplayName("[DECEMBER 이벤트]증정품 여부 반환 테스트[true]")
    @Test
    void isExistTrue() {
        // given
        GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
        Amount amount = new Amount(120_000);

        // when
        Giveaway giveaway = new Giveaway(giveawayType, amount);

        // then
        Assertions.assertThat(giveaway.isExist()).isTrue();
    }

    @DisplayName("[DECEMBER 이벤트]기준 금액을 넘었을 때, 샴페인 증정 테스트")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 130_000, 200_000, 1_000_000})
    void getChampagne(int price) {
        // given
        GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
        Amount amount = new Amount(price);

        // when
        Giveaway giveaway = new Giveaway(giveawayType, amount);
        int size = giveaway.getMenus().size();

        // then
        Assertions.assertThat(size).isEqualTo(1);
        Assertions.assertThat(giveaway.getMenus()).containsKey(Menu.CHAMPAGNE);
        Assertions.assertThat(giveaway.getMenus().get(Menu.CHAMPAGNE)).isEqualTo(1);
    }

    @DisplayName("[DECEMBER 이벤트]기준 금액을 넘지 못했을 때, 샴페인 증정 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 5_000, 100_000, 110_000, 119_000})
    void getChampagneZero(int price) {
        // given
        GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
        Amount amount = new Amount(price);

        // when
        Giveaway giveaway = new Giveaway(giveawayType, amount);

        // then
        Assertions.assertThat(giveaway.getMenus()).containsKey(Menu.NONE);
    }

    @DisplayName("[DECEMBER 이벤트]증정품 총 금액 반환 테스트[샴페인 1개 가격]")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 130_000, 200_000, 1_000_000})
    void getTotalPrice(int price) {
        // given
        GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
        Amount amount = new Amount(price);

        // when
        Giveaway giveaway = new Giveaway(giveawayType, amount);

        // then
        Assertions.assertThat(giveaway.getTotalPrice()).isEqualTo(Menu.CHAMPAGNE.getPrice());
    }

    @DisplayName("증정품 총 금액 반환 테스트[0원]")
    @ParameterizedTest
    @ValueSource(ints = {0, 5_000, 100_000, 110_000, 119_000})
    void getTotalPriceZero(int price) {
        // given
        GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
        Amount amount = new Amount(price);

        // when
        Giveaway giveaway = new Giveaway(giveawayType, amount);

        // then
        Assertions.assertThat(giveaway.getTotalPrice()).isEqualTo(0);
    }
}
