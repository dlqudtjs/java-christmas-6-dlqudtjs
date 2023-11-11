package christmas.model;

import christmas.constant.ExceptionMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {

    @DisplayName("방문일이 존재하지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32, 100})
    void invalidVisitDate(int visitDate) {
        Assertions.assertThatThrownBy(() -> new VisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
    }

    @DisplayName("방문일이 존재하면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
            30, 31})
    void validVisitDate(int visitDate) {
        Assertions.assertThatCode(() -> new VisitDate(visitDate))
                .doesNotThrowAnyException();
    }

    @DisplayName("Day 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
            30, 31})
    void getDay(int visitDate) {
        VisitDate date = new VisitDate(visitDate);
        Assertions.assertThat(date.getDay()).isEqualTo(visitDate);
    }
}
