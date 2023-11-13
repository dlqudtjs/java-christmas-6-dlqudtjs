package christmas.model;

import christmas.model.event.Benefit;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BenefitTest {

    @DisplayName("이벤트 최소 금액을 넘지 않았을 때, 아무 혜택도 받지 못한다. [최소 금액 10,000원]")
    @ParameterizedTest
    @MethodSource("provideBenefitWhenAmountIsLessThanMinimum")
    void getBenefitWhenAmountIsLessThanMinimum(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        int benefitPrice = benefit.getTotalBenefitAmount().getValue();

        // then
        Assertions.assertThat(benefitPrice).isEqualTo(0);
    }

    @DisplayName("이벤트 최소 금액을 넘었을 때, 혜택을 받는다. [최소 금액 10,000원]")
    @ParameterizedTest
    @MethodSource("provideBenefitWhenAmountIsGreaterThanMinimum")
    void getBenefitWhenAmountIsGreaterThanMinimum(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        int benefitPrice = benefit.getTotalBenefitAmount().getValue();

        // then
        Assertions.assertThat(benefitPrice).isGreaterThan(0);
    }

    private static Stream<BookingInfo> provideBenefitWhenAmountIsLessThanMinimum() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("양송이수프", 1)), new VisitDate(1)),
                new BookingInfo(new Order(
                        Map.of("아이스크림", 1)), new VisitDate(1)),
                new BookingInfo(new Order(
                        Map.of("타파스", 1, "제로콜라", 1)), new VisitDate(1))

        );
    }

    private static Stream<BookingInfo> provideBenefitWhenAmountIsGreaterThanMinimum() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("양송이수프", 1, "티본스테이크", 1)), new VisitDate(1)),
                new BookingInfo(new Order(
                        Map.of("아이스크림", 1, "티본스테이크", 1)), new VisitDate(1)),
                new BookingInfo(new Order(
                        Map.of("타파스", 1, "제로콜라", 1, "티본스테이크", 1)), new VisitDate(1))

        );
    }
}
