package lotto.exception;

public enum MoneyErrorMessage {
    NEGATIVE_AMOUNT("금액은 음수가 될 수 없습니다."),
    NOT_DIVISIBLE_BY_THOUSAND("금액은 1,000원 단위로 입력해야 합니다."),
    OUT_OF_INTEGER_RANGE("금액이 int 자료형의 범위를 초과했습니다."),
    NOT_NUMERIC("금액은 숫자만 입력 가능합니다.");

    private final String message;

    MoneyErrorMessage(String message) {
        this.message = message;
    }

    public String text() {
        String prefix = "[ERROR] ";
        return prefix + message;
    }
}
