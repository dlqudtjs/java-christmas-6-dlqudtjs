package christmas.constant;

public enum PlannerMessage {

    PLANNER_START_MESSAGE("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다."),
    INPUT_VISIT_DATE_MESSAGE("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_MENU_COUNT_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_BENEFIT_PREVIEW_MESSAGE("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    OUTPUT_ORDER_DETAILS_TITLE_MESSAGE("<주문 메뉴>"),
    OUTPUT_TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT_TITLE_MESSAGE("<할인 전 총주문 금액>"),
    OUTPUT_GIVEAWAY_MENU_TITLE_MESSAGE("<증정 메뉴>"),
    OUTPUT_BENEFIT_DETAILS_TITLE_MESSAGE("<혜택 내역>"),
    OUTPUT_TOTAL_BENEFIT_AMOUNT_TITLE_MESSAGE("<총혜택 금액>"),
    OUTPUT_TOTAL_EXPECTED_PAYMENT_AFTER_DISCOUNT_TITLE_MESSAGE("<할인 후 예상 결제 금액>"),
    OUTPUT_EVENT_BADGE_TITLE_MESSAGE("<%d월 이벤트 배지>"),
    OUTPUT_NO_DETAIL_MESSAGE("없음");

    private final String message;

    PlannerMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
