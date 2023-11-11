package christmas.model;

import christmas.constant.ExceptionMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {

    // 유효성 테스트
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

    // 기능 목록 테스트
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

    @DisplayName("주말 여부 반환 테스트 [ture]")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void isWeekend(int visitDate) {
        VisitDate date = new VisitDate(visitDate);
        Assertions.assertThat(date.isWeekend()).isTrue();
    }

    @DisplayName("주말 여부 반환 테스트 [false]")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14,
            17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void isNotWeekend(int visitDate) {
        VisitDate date = new VisitDate(visitDate);
        Assertions.assertThat(date.isWeekend()).isFalse();
    }

    @DisplayName("특별한 날 여부 반환 테스트 [ture]")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void isSpecialDay(int visitDate) {
        VisitDate date = new VisitDate(visitDate);
        Assertions.assertThat(date.isSpecialDay()).isTrue();
    }

    @DisplayName("특별한 날 여부 반환 테스트 [false]")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12,
            13, 14, 15, 16, 18, 19, 20, 21, 22, 23,
            26, 27, 28, 29, 30})
    void isNotSpecialDay(int visitDate) {
        VisitDate date = new VisitDate(visitDate);
        Assertions.assertThat(date.isSpecialDay()).isFalse();
    }
}
