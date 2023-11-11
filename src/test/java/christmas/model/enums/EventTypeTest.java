package christmas.model.enums;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EventTypeTest {

    @DisplayName("이벤트 활성화 여부 테스트")
    @ParameterizedTest
    @CsvSource(value = {"CHRISTMAS_D_DAY_DISCOUNT, true, 크리스마스 디데이 할인", "WEEKDAY_DISCOUNT, true, 평일 할인",
            "WEEKEND_DISCOUNT, true, 주말 할인", "GIFT_EVENT, true, 증정 이벤트"})
    void isActive(EventType eventType, boolean expected, String name) {
        // then
        Assertions.assertThat(eventType.isActive()).isEqualTo(expected);
        Assertions.assertThat(eventType.getName()).isEqualTo(name);
    }

    @DisplayName("활성화된 이벤트 타입 반환 테스트")
    @Test
    void getActiveEventTypes() {
        // given && when
        List<EventType> eventTypes = EventType.getActiveEventTypes();

        // then
        for (EventType eventType : eventTypes) {
            Assertions.assertThat(eventType.isActive()).isTrue();
        }
    }
}
