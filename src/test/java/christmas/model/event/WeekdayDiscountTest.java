package christmas.model.event;

import christmas.model.BookingInfo;
import christmas.model.Order;
import christmas.model.VisitDate;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WeekdayDiscountTest {

    private int WEEKDAY_DISCOUNT_PRICE = 2_023;

    @DisplayName("평일 할인 테스트[이벤트 기간 적용 테스트]")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14,
            17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void weekdayDiscountTest(int date) {
        // given & when
        int menuCount = 3;
        BookingInfo bookingInfo = new BookingInfo(new Order(Map.of("초코케이크", menuCount)), new VisitDate(date));
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(bookingInfo);

        int expectedPrice = WEEKDAY_DISCOUNT_PRICE * menuCount;
        int discountPrice = weekdayDiscount.getDiscount().getValue();

        Assertions.assertThat(discountPrice).isEqualTo(expectedPrice);
    }

    @DisplayName("평일 할인 테스트[이벤트 기간 적용 테스트[0원]]")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void weekdayDiscountTestFail(int date) {
        // given & when
        int menuCount = 3;
        BookingInfo bookingInfo = new BookingInfo(new Order(Map.of("초코케이크", menuCount)), new VisitDate(date));
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(bookingInfo);

        int discountPrice = weekdayDiscount.getDiscount().getValue();

        // then
        Assertions.assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("평일이지만 디저트 메뉴가 없으면 할인이 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14,
            17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void weekdayDiscountTestFailWhenNoDessertMenu(int date) {
        // given & when
        int menuCount = 3;
        BookingInfo bookingInfo = new BookingInfo(new Order(Map.of("티본스테이크", menuCount)), new VisitDate(date));
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(bookingInfo);

        int discountPrice = weekdayDiscount.getDiscount().getValue();

        // then
        Assertions.assertThat(discountPrice).isEqualTo(0);
    }
}
