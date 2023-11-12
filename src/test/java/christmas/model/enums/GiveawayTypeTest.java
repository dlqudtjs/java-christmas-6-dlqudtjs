package christmas.model.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GiveawayTypeTest {

    @DisplayName("December 증정품 여부 반환 테스트[false]")
    @ParameterizedTest
    @ValueSource(ints = {0, 5_000, 100_000, 110_000, 119_000})
    void isAvailableFalse(int price) {
        GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
        Assertions.assertThat(giveawayType.isAvailable(price)).isFalse();
    }

    @DisplayName("December 증정품 여부 반환 테스트[true]")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 130_000, 200_000, 1_000_000})
    void isAvailableTrue(int price) {
        GiveawayType giveawayType = GiveawayType.DECEMBER_GIVEAWAY;
        Assertions.assertThat(giveawayType.isAvailable(price)).isTrue();
    }
}
