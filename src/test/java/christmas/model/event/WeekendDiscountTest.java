package christmas.model.event;

import christmas.model.BookingInfo;
import christmas.model.Order;
import christmas.model.VisitDate;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WeekendDiscountTest {

    private int WEEKEND_DISCOUNT_PRICE = 2_023;

    @DisplayName("주말 할인 테스트[이벤트 기간 적용 테스트]")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void weekendDiscountTest(int date) {
        // given & when
        int menuCount = 3;
        BookingInfo bookingInfo = new BookingInfo(new Order(Map.of("티본스테이크", menuCount)), new VisitDate(date));
        WeekendDiscount weekendDiscount = new WeekendDiscount(bookingInfo);

        int expectedPrice = WEEKEND_DISCOUNT_PRICE * menuCount;
        int discountPrice = weekendDiscount.getDiscount().getValue();

        Assertions.assertThat(discountPrice).isEqualTo(expectedPrice);
    }

    @DisplayName("주말 할인 테스트[이벤트 기간 적용 테스트[0원]]")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14,
            17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void weekendDiscountTestFail(int date) {
        // given & when
        int menuCount = 3;
        BookingInfo bookingInfo = new BookingInfo(new Order(Map.of("티본스테이크", menuCount)), new VisitDate(date));
        WeekendDiscount weekendDiscount = new WeekendDiscount(bookingInfo);

        int discountPrice = weekendDiscount.getDiscount().getValue();

        // then
        Assertions.assertThat(discountPrice).isEqualTo(0);
    }
}
