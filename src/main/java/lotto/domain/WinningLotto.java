package lotto.domain;

import java.util.List;
import lotto.exception.LottoErrorMessage;
import lotto.exception.WinningLottoErrorMessage;

public class WinningLotto {

    private final Lotto lotto;

    public WinningLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    public static WinningLotto from(String winningNumbers) {
        validateWinningNumbers(winningNumbers);
        return new WinningLotto(Lotto.from(winningNumbers));
    }

    private static void validateWinningNumbers(String winningNumbers) {
        shouldThrowExceptionInvalidCharacter(winningNumbers);
        shouldThrowExceptionInvalidCount(winningNumbers);
        shouldThrowExceptionEmptyInput(winningNumbers);
        shouldThrowExceptionContainsWhitespace(winningNumbers);
        shouldThrowExceptionWhenOutOfRange(winningNumbers);
        shouldThrowExceptionWhenDuplicatedNumber(winningNumbers);
    }

    private static void shouldThrowExceptionInvalidCharacter(String winningNumbers) {
        try {
            Lotto.from(winningNumbers);
        } catch (IllegalArgumentException e) {
            String exceptionMessage = e.getMessage();
            if (exceptionMessage.equals(LottoErrorMessage.INVALID_CHARACTER.text())) {
                throw new IllegalArgumentException(
                    WinningLottoErrorMessage.INVALID_CHARACTER.text());
            }
        }
    }

    private static void shouldThrowExceptionInvalidCount(String winningNumbers) {
        try {
            Lotto.from(winningNumbers);
        } catch (IllegalArgumentException e) {
            String exceptionMessage = e.getMessage();
            if (exceptionMessage.equals(LottoErrorMessage.INVALID_COUNT.text())) {
                throw new IllegalArgumentException(WinningLottoErrorMessage.INVALID_COUNT.text());
            }
        }
    }

    private static void shouldThrowExceptionEmptyInput(String winningNumbers) {
        try {
            Lotto.from(winningNumbers);
        } catch (IllegalArgumentException e) {
            String exceptionMessage = e.getMessage();
            if (exceptionMessage.equals(LottoErrorMessage.EMPTY_INPUT.text())) {
                throw new IllegalArgumentException(WinningLottoErrorMessage.EMPTY_INPUT.text());
            }
        }
    }

    private static void shouldThrowExceptionContainsWhitespace(String winningNumbers) {
        try {
            Lotto.from(winningNumbers);
        } catch (IllegalArgumentException e) {
            String exceptionMessage = e.getMessage();
            if (exceptionMessage.equals(LottoErrorMessage.CONTAINS_WHITESPACE.text())) {
                throw new IllegalArgumentException(
                    WinningLottoErrorMessage.CONTAINS_WHITESPACE.text());
            }
        }
    }

    private static void shouldThrowExceptionWhenOutOfRange(String winningNumbers) {
        try {
            Lotto.from(winningNumbers);
        } catch (IllegalArgumentException e) {
            String exceptionMessage = e.getMessage();
            if (exceptionMessage.equals(LottoErrorMessage.OUT_OF_RANGE.text())) {
                throw new IllegalArgumentException(
                    WinningLottoErrorMessage.OUT_OF_RANGE.text());
            }
        }
    }

    private static void shouldThrowExceptionWhenDuplicatedNumber(String winningNumbers) {
        try {
            Lotto.from(winningNumbers);
        } catch (IllegalArgumentException e) {
            String exceptionMessage = e.getMessage();
            if (exceptionMessage.equals(LottoErrorMessage.DUPLICATED_NUMBER.text())) {
                throw new IllegalArgumentException(
                    WinningLottoErrorMessage.DUPLICATED_NUMBER.text());
            }
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public List<Integer> getNumbers() {
        return lotto.getNumbers();
    }
}
