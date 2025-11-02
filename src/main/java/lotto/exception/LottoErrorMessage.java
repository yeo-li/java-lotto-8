package lotto.exception;

public enum LottoErrorMessage {
    INVALID_CHARACTER("로또 번호에는 숫자와 컴마(,)만 포함될 수 있습니다."),
    INVALID_COUNT("로또 번호는 6개여야 합니다."),
    OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATED_NUMBER("로또 번호에 중복된 숫자가 존재합니다.");

    private final String message;

    LottoErrorMessage(String message) {
        this.message = message;
    }

    public String text() {
        String prefix = "[ERROR] ";
        return prefix + message;
    }
}