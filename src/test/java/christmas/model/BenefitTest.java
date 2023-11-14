package christmas.model;

import christmas.model.enums.BadgeType;
import christmas.model.enums.EventType;
import christmas.model.event.Benefit;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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

    @DisplayName("할인 리스트 반환 테스트[평일, 증정 이벤트, 크리스마스 디데이 이벤트 적용]")
    @ParameterizedTest
    @MethodSource("provideBookingInfo1")
    void getEventDetailTest1(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        Map<EventType, Amount> eventDetails = benefit.getEventDetails();

        // then
        Assertions.assertThat(eventDetails).containsOnlyKeys(
                EventType.WEEKDAY_DISCOUNT,
                EventType.GIFT_EVENT,
                EventType.CHRISTMAS_D_DAY_DISCOUNT
        );
    }

    @DisplayName("할인 리스트 반환 테스트[평일, 증정 이벤트, 크리스마스 디데이 이벤트, 특별 할인 적용]")
    @ParameterizedTest
    @MethodSource("provideBookingInfo2")
    void getEventDetailTest2(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        Map<EventType, Amount> eventDetails = benefit.getEventDetails();

        // then
        Assertions.assertThat(eventDetails).containsOnlyKeys(
                EventType.WEEKDAY_DISCOUNT,
                EventType.GIFT_EVENT,
                EventType.CHRISTMAS_D_DAY_DISCOUNT,
                EventType.SPECIAL_DISCOUNT
        );
    }

    @DisplayName("할인 리스트 반환 테스트[주말 할인 적용]")
    @ParameterizedTest
    @MethodSource("provideBookingInfo3")
    void getEventDetailTest3(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        Map<EventType, Amount> eventDetails = benefit.getEventDetails();

        // then
        Assertions.assertThat(eventDetails).containsOnlyKeys(
                EventType.WEEKEND_DISCOUNT
        );
    }

    @DisplayName("할인 리스트 반환 테스트[평일 할인 적용]")
    @ParameterizedTest
    @MethodSource("provideBookingInfo4")
    void getEventDetailTest4(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        Map<EventType, Amount> eventDetails = benefit.getEventDetails();

        // then
        Assertions.assertThat(eventDetails).containsOnlyKeys(
                EventType.WEEKDAY_DISCOUNT
        );
    }

    @DisplayName("할인 리스트 반환 테스트[증정 이벤트 적용]")
    @ParameterizedTest
    @MethodSource("provideBookingInfo5")
    void getEventDetailTest5(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        Map<EventType, Amount> eventDetails = benefit.getEventDetails();

        // then
        Assertions.assertThat(eventDetails).containsOnlyKeys(
                EventType.GIFT_EVENT
        );
    }

    @DisplayName("할인 리스트 반환 테스트[크리스마스 디데이 할인 적용]")
    @ParameterizedTest
    @MethodSource("provideBookingInfo6")
    void getEventDetailTest6(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        Map<EventType, Amount> eventDetails = benefit.getEventDetails();

        // then
        Assertions.assertThat(eventDetails).containsOnlyKeys(
                EventType.CHRISTMAS_D_DAY_DISCOUNT
        );
    }

    @DisplayName("할인 리스트 반환 테스트[특별 할인 적용]")
    @ParameterizedTest
    @MethodSource("provideBookingInfo7")
    void getEventDetailTest7(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        Map<EventType, Amount> eventDetails = benefit.getEventDetails();

        // then
        Assertions.assertThat(eventDetails).containsOnlyKeys(
                EventType.SPECIAL_DISCOUNT
        );
    }

    @DisplayName("총 혜택 금액 반환 테스트[31423원]")
    @ParameterizedTest
    @MethodSource("provideTotalBenefitAmount1")
    void getTotalBenefitAmountTest(BookingInfo bookingInfo, int expectedBenefitPrice) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        int benefitPrice = benefit.getTotalBenefitAmount().getValue();

        // then
        Assertions.assertThat(benefitPrice).isEqualTo(expectedBenefitPrice);
    }

    @DisplayName("총 혜택 금액 반환 테스트[31069원]")
    @ParameterizedTest
    @MethodSource("provideTotalBenefitAmount2")
    void getTotalBenefitAmountTest2(BookingInfo bookingInfo, int expectedBenefitPrice) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        int benefitPrice = benefit.getTotalBenefitAmount().getValue();

        // then
        Assertions.assertThat(benefitPrice).isEqualTo(expectedBenefitPrice);
    }

    @DisplayName("[혜택 금액 : 3023] 뱃지 반환 테스트 [NONE]")
    @ParameterizedTest
    @MethodSource("provideNONEBadge")
    void getBadgeTest1(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        BadgeType badge = benefit.getBadge();

        // then
        Assertions.assertThat(badge).isEqualTo(BadgeType.NONE);
    }

    @DisplayName("[혜택 금액 : 5046] 뱃지 반환 테스트[별]")
    @ParameterizedTest
    @MethodSource("provideStarBadge")
    void getBadgeTest2(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        BadgeType badge = benefit.getBadge();

        // then
        Assertions.assertThat(badge).isEqualTo(BadgeType.STAR);
    }

    @DisplayName("[혜택 금액 : 11415] 뱃지 반환 테스트[트리]")
    @ParameterizedTest
    @MethodSource("provideTreeBadge")
    void getBadgeTest3(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        BadgeType badge = benefit.getBadge();

        // then
        Assertions.assertThat(badge).isEqualTo(BadgeType.TREE);
    }

    @DisplayName("[혜택 금액 : 2만원 이상] 뱃지 반환 테스트[산타]")
    @ParameterizedTest
    @MethodSource("provideSantaBadge")
    void getBadgeTest4(BookingInfo bookingInfo) {
        // given & when
        Benefit benefit = new Benefit(bookingInfo);
        BadgeType badge = benefit.getBadge();

        // then
        Assertions.assertThat(badge).isEqualTo(BadgeType.SANTA);
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
                        Map.of("아이스크림", 2)), new VisitDate(1)),
                new BookingInfo(new Order(
                        Map.of("타파스", 1, "제로콜라", 1, "티본스테이크", 1)), new VisitDate(1))

        );
    }

    // [평일, 증정 이벤트, 크리스마스 디데이 이벤트 적용 BookingInfo]
    private static Stream<BookingInfo> provideBookingInfo1() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("초코케이크", 1, "티본스테이크", 3)), new VisitDate(4)),
                new BookingInfo(new Order(
                        Map.of("아이스크림", 1, "티본스테이크", 3)), new VisitDate(14)),
                new BookingInfo(new Order(
                        Map.of("아이스크림", 2, "티본스테이크", 2)), new VisitDate(21))

        );
    }

    // [평일, 증정 이벤트, 크리스마스 디데이 이벤트, 특별 할인 적용 BookingInfo]
    private static Stream<BookingInfo> provideBookingInfo2() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("초코케이크", 1, "티본스테이크", 3)), new VisitDate(25))

        );
    }

    // [주말 할인 적용 BookingInfo]
    private static Stream<BookingInfo> provideBookingInfo3() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("티본스테이크", 1)), new VisitDate(29)),
                new BookingInfo(new Order(
                        Map.of("티본스테이크", 1)), new VisitDate(30))

        );
    }

    // [평일 할인 적용 BookingInfo]
    private static Stream<BookingInfo> provideBookingInfo4() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("초코케이크", 1)), new VisitDate(26)),
                new BookingInfo(new Order(
                        Map.of("아이스크림", 3)), new VisitDate(28))
        );
    }

    // [증정 이벤트 적용 BookingInfo]
    private static Stream<BookingInfo> provideBookingInfo5() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("티본스테이크", 4)), new VisitDate(26)),
                new BookingInfo(new Order(
                        Map.of("양송이수프", 20)), new VisitDate(30))
        );
    }

    // [크리스마스 디데이 할인 적용 BookingInfo]
    private static Stream<BookingInfo> provideBookingInfo6() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("아이스크림", 2)), new VisitDate(1)),
                new BookingInfo(new Order(
                        Map.of("초코케이크", 1)), new VisitDate(23))
        );
    }

    // [특별 할인 적용 BookingInfo]
    private static Stream<BookingInfo> provideBookingInfo7() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("티본스테이크", 1)), new VisitDate(31))
        );
    }

    // [평일, 크리스마스 디데이, 증정품, 할인 적용]
    private static Stream<Arguments> provideTotalBenefitAmount1() {
        return Stream.of(
                Arguments.of(new BookingInfo(new Order(
                        Map.of("티본스테이크", 1, "레드와인", 1, "초코케이크", 1)), new VisitDate(25)), 31423)
        );
    }

    // [주말 메뉴 3개, 증정품 할인 적용]
    private static Stream<Arguments> provideTotalBenefitAmount2() {
        return Stream.of(
                Arguments.of(new BookingInfo(new Order(
                        Map.of("티본스테이크", 3, "레드와인", 1, "초코케이크", 1)), new VisitDate(30)), 31069)
        );
    }

    // [뱃지 반환 테스트 [혜택 금액 : 3023]]
    private static Stream<BookingInfo> provideNONEBadge() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("티본스테이크", 1)), new VisitDate(1))
        );
    }

    // [뱃지 반환 테스트[혜택 금액 : 5046]]
    private static Stream<BookingInfo> provideStarBadge() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("티본스테이크", 2)), new VisitDate(1))
        );
    }

    // [뱃지 반환 테스트[혜택 금액 : 11415]]
    private static Stream<BookingInfo> provideTreeBadge() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("아이스크림", 5)), new VisitDate(4))
        );
    }

    // [뱃지 반환 테스트[혜택 금액 : 2만원 이상]]
    private static Stream<BookingInfo> provideSantaBadge() {
        return Stream.of(
                new BookingInfo(new Order(
                        Map.of("티본스테이크", 5)), new VisitDate(4))
        );
    }
}
