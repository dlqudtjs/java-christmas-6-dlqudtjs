package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.BookingInfo;
import christmas.model.Order;
import christmas.model.VisitDate;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ChristmasDDayDiscountTest {

    private final int christmasDDayDiscountPrice = 100;
    private final int initPrice = 1_000;

    @DisplayName("크리스마스 디데이 할인 테스트 [25일까지]")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25})
    void christmasDDayDiscountTest(int date) {
        // given & when
        BookingInfo bookingInfo = new BookingInfo(new Order(Map.of("티본스테이크", 1)), new VisitDate(date));
        ChristmasDDayDiscount christmasDDayDiscount = new ChristmasDDayDiscount(bookingInfo);

        int discountPrice = christmasDDayDiscount.getDiscount().getValue();
        int expectedPrice = initPrice + (bookingInfo.getVisitDate().getDay() * christmasDDayDiscountPrice);
        expectedPrice -= 100;

        // then
        assertThat(discountPrice).isEqualTo(expectedPrice);
    }

    @DisplayName("크리스마스 디데이 할인 테스트 [25일 이후 [0원]]")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void christmasDDayDiscountTestFail(int date) {
        // given & when
        BookingInfo bookingInfo = new BookingInfo(new Order(Map.of("티본스테이크", 1)), new VisitDate(date));
        ChristmasDDayDiscount christmasDDayDiscount = new ChristmasDDayDiscount(bookingInfo);

        int discountPrice = christmasDDayDiscount.getDiscount().getValue();

        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}
