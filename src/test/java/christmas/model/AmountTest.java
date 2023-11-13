package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AmountTest {

    @DisplayName("할인 후 금액 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 5_000, 100_000, 110_000, 119_000})
    void getDiscountedPrice(int price) {
        // given
        Amount amount = new Amount(price);

        // when
        Amount minusPrice = new Amount(5_000);
        Amount expectedPrice = new Amount(price - 5_000);
        amount.discount(minusPrice);

        // then
        Assertions.assertThat(amount.getValue()).isEqualTo(expectedPrice.getValue());
    }

    @DisplayName("금액 추가 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 5_000, 100_000, 110_000, 119_000})
    void add(int price) {
        // given
        Amount amount = new Amount(price);

        // when
        Amount plusPrice = new Amount(5_000);
        Amount expectedPrice = new Amount(price + 5_000);
        amount.add(plusPrice);

        // then
        Assertions.assertThat(amount.getValue()).isEqualTo(expectedPrice.getValue());
    }
}
