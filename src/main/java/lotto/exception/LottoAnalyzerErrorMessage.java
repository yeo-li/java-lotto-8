package lotto.exception;

public enum LottoAnalyzerErrorMessage {
    INVALID_CHARACTER("보너스 번호에는 숫자만 포함될 수 있습니다."),
    OUT_OF_RANGE("보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATED_NUMBER("보너스 번호가 당첨 번호와 중복 되었습니다.");

    private final String message;

    LottoAnalyzerErrorMessage(String message) {
        this.message = message;
    }

    public String text() {
        return "[ERROR] " + message;
    }
}