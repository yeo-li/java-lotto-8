package lotto.exception;

public enum WinningLottoErrorMessage {
    INVALID_CHARACTER("당첨 번호에는 숫자와 컴마(,)만 포함될 수 있습니다."),
    INVALID_COUNT("당첨 번호는 6개여야 합니다."),
    OUT_OF_RANGE("당첨 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATED_NUMBER("당첨 번호에 중복된 숫자가 존재합니다."),
    EMPTY_INPUT("당첨 번호가 입력되지 않았습니다."),
    CONTAINS_WHITESPACE("당첨 번호에 공백이 포함될 수 없습니다.");

    private final String message;

    WinningLottoErrorMessage(String message) {
        this.message = message;
    }

    public String text() {
        String prefix = "[ERROR] ";
        return prefix + message;
    }
}
