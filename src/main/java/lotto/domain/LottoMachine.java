package lotto.domain;

import lotto.Lotto;

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
