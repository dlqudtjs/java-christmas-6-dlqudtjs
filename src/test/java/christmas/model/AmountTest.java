package christmas.model;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AmountTest {

    @DisplayName("할인 후 금액 반환 테스트[Minus 1_000]")
    @ParameterizedTest
    @MethodSource("getDiscountedPrice")
    void getDiscountedPrice(int price, int discountPrice) {
        // given
        Amount amount = new Amount(price);

        // when
        Amount minusPrice = new Amount(1_000);
        amount.discount(minusPrice);

        // then
        Assertions.assertThat(amount.getValue()).isEqualTo(discountPrice);
    }

    @DisplayName("금액 추가 테스트[Plus 1_000]")
    @ParameterizedTest
    @MethodSource("getPlusPrice")
    void addPrice(int price, int plusPrice) {
        // given
        Amount amount = new Amount(price);

        // when
        Amount addPrice = new Amount(1_000);
        amount.add(addPrice);

        // then
        Assertions.assertThat(amount.getValue()).isEqualTo(plusPrice);
    }

    private static Stream<Arguments> getDiscountedPrice() {
        return Stream.of(
                Arguments.of(1_000, 0),
                Arguments.of(5_000, 4_000),
                Arguments.of(100_000, 99_000),
                Arguments.of(110_000, 109_000),
                Arguments.of(119_000, 118_000)
        );
    }

    private static Stream<Arguments> getPlusPrice() {
        return Stream.of(
                Arguments.of(1_000, 2_000),
                Arguments.of(5_000, 6_000),
                Arguments.of(100_000, 101_000),
                Arguments.of(110_000, 111_000),
                Arguments.of(119_000, 120_000)
        );
    }
}
