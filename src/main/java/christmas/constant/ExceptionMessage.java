package christmas.constant;

public enum ExceptionMessage {

    INVALID_VISIT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_COUNT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_MAX_COUNT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_NAME("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_ONLY_DRINK("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_COUNT_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ERROR_TAG("[ERROR] ");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_TAG + message;
    }
}
