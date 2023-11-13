package christmas.model.event;

import christmas.model.BookingInfo;
import christmas.model.Order;
import christmas.model.VisitDate;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SpecialDiscountTest {

    @DisplayName("특별 할인 테스트[이벤트 기간 적용 테스트]")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void specialDiscountTest(int date) {
        // given & when
        BookingInfo bookingInfo = new BookingInfo(new Order(Map.of("티본스테이크", 1)), new VisitDate(date));
        SpecialDiscount specialDiscount = new SpecialDiscount(bookingInfo);

        int discountPrice = specialDiscount.getDiscount().getValue();
        int expectedPrice = 1_000;

        // then
        Assertions.assertThat(discountPrice).isEqualTo(expectedPrice);
    }

    @DisplayName("특별 할인 테스트[이벤트 기간 적용 테스트[0원]]")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12,
            13, 14, 15, 16, 18, 19, 20, 21, 22, 23,
            26, 27, 28, 29, 30})
    void specialDiscountTestFail(int date) {
        // given & when
        BookingInfo bookingInfo = new BookingInfo(new Order(Map.of("티본스테이크", 1)), new VisitDate(date));
        SpecialDiscount specialDiscount = new SpecialDiscount(bookingInfo);

        int discountPrice = specialDiscount.getDiscount().getValue();

        // then
        Assertions.assertThat(discountPrice).isEqualTo(0);
    }
}
