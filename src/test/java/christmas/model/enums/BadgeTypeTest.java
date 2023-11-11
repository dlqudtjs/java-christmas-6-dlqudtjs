package christmas.model.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BadgeTypeTest {

    @DisplayName("가격에 따른 배지 타입 반환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"0, 없음", "1, 없음", "4999, 없음", "5000, 별", "9999, 별",
            "10000, 트리", "19999, 트리", "20000, 산타", "20001, 산타", "29999, 산타"})
    void getBadgeWithPrice(int price, String expected) {
        // when
        BadgeType badgeType = BadgeType.getBadgeWithPrice(price);

        // then
        Assertions.assertThat(badgeType.getName()).isEqualTo(expected);
    }
}
