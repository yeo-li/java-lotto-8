package lotto.domain;

import java.util.List;
import lotto.exception.LottoAnalyzerErrorMessage;

public class LottoAnalyzer {

    private final WinningLotto winningNumbers;
    private final int bonusNumber;

    private LottoAnalyzer(WinningLotto winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoAnalyzer from(WinningLotto winningLotto, String bonusNumber) {
        validateBonusNumber(winningLotto, bonusNumber);
        return new LottoAnalyzer(winningLotto, Integer.parseInt(bonusNumber));
    }

    private static void validateBonusNumber(WinningLotto winningLotto, String bonusNumber) {
        shouldThrowExceptionWhenInvalidCharacter(bonusNumber);
        shouldThrowExceptionWhenOutOfRange(bonusNumber);
        shouldThrowExceptionWhenDuplicated(winningLotto, bonusNumber);
    }

    private static void shouldThrowExceptionWhenInvalidCharacter(String bonusNumber) {
        if (!bonusNumber.matches("[0-9,]+")) {
            throw new IllegalArgumentException(LottoAnalyzerErrorMessage.INVALID_CHARACTER.text());
        }
    }

    private static void shouldThrowExceptionWhenOutOfRange(String bonusNumber) {
        try {
            int number = Integer.parseInt(bonusNumber);
            if (number < 1 || 45 < number) {
                throw new IllegalArgumentException(LottoAnalyzerErrorMessage.OUT_OF_RANGE.text());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(LottoAnalyzerErrorMessage.OUT_OF_RANGE.text());
        }
    }

    private static void shouldThrowExceptionWhenDuplicated(WinningLotto winningLotto,
        String input) {
        int bonusNumber = Integer.parseInt(input);
        List<Integer> numbers = winningLotto.getNumbers();
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(LottoAnalyzerErrorMessage.DUPLICATED_NUMBER.text());
        }
    }

    public WinningLotto getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
