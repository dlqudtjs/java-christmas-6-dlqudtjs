package christmas.model.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EventDateTypeTest {

    @DisplayName("특별 날짜 포함 여부 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, false", "2, false", "3, true", "4, false", "5, false", "6, false", "7, false", "8, false",
            "9, false", "10, true", "11, false", "12, false", "13, false", "14, false", "15, false", "16, false",
            "17, true", "18, false", "19, false", "20, false", "21, false", "22, false", "23, false", "24, true",
            "25, true", "26, false", "27, false", "28, false", "29, false", "30, false", "31, true"})
    void isSpecialDay(int date, boolean expected) {
        // when
        boolean actual = EventDateType.SPECIAL_DAY.is(date);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
