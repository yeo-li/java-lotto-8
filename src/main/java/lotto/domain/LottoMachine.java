package lotto.domain;

import lotto.exception.LottoMachineErrorMessage;

public class LottoMachine {

    private final WinningLotto winningNumbers;
    private final int bonusNumber;


    private LottoMachine(WinningLotto winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoMachine from(WinningLotto winningLotto, String bonusNumber) {
        validateBonusNumber(bonusNumber);
        return new LottoMachine(winningLotto, Integer.parseInt(bonusNumber));
    }


    private static void validateBonusNumber(String bonusNumber) {
        shouldThrowExceptionWhenInvalidCharacter(bonusNumber);
    }

    private static void shouldThrowExceptionWhenInvalidCharacter(String bonusNumber) {
        if (!bonusNumber.matches("[0-9,]+")) {
            throw new IllegalArgumentException(LottoMachineErrorMessage.INVALID_CHARACTER.text());
        }
    }

    public WinningLotto getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
