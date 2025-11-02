package lotto.domain;

import lotto.Lotto;
import lotto.exception.LottoMachineErrorMessage;

public class LottoMachine {

    private final Lotto winningNumbers;
    private final int bonusNumber;


    private LottoMachine(Lotto winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoMachine from(String winningNumbers, String bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(bonusNumber);

        return new LottoMachine(Lotto.from(winningNumbers), Integer.parseInt(bonusNumber));
    }

    private static void validateWinningNumbers(String winningNumbers) {
        shouldThrowExceptionInvalidCharacter(winningNumbers);
    }

    private static void shouldThrowExceptionInvalidCharacter(String winningNumbers) {
        try {
            Lotto.from(winningNumbers);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(LottoMachineErrorMessage.INVALID_CHARACTER.text());
        }

    }

    private static void validateBonusNumber(String bonusNumber) {

    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
