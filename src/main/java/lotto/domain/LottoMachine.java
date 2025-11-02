package lotto.domain;

public class LottoMachine {

    private final WinningLotto winningNumbers;
    private final int bonusNumber;


    public LottoMachine(WinningLotto winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoMachine from(WinningLotto winningLotto, String bonusNumber) {
        validateBonusNumber(bonusNumber);
        return new LottoMachine(winningLotto, Integer.parseInt(bonusNumber));
    }


    private static void validateBonusNumber(String bonusNumber) {

    }

    public WinningLotto getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
