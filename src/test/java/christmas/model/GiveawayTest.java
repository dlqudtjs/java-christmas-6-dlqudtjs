package christmas.model;

import christmas.model.enums.GiveawayType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiveawayTest {

    @DisplayName("증정품 여부 반환 테스트[false]")
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

    @DisplayName("증정품 여부 반환 테스트[true]")
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

}
