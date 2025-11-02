package lotto.domain;

import java.util.Arrays;
import java.util.List;
import lotto.util.Parser;

public class LottoMachine {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;


    private LottoMachine(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoMachine from(String winningNumbers, String bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(bonusNumber);

        List<Integer> parsedWinningNumbers = Arrays.stream(Parser.parseInput(winningNumbers))
            .map(Integer::parseInt)
            .toList();

        return new LottoMachine(parsedWinningNumbers, Integer.parseInt(bonusNumber));
    }

    private static void validateWinningNumbers(String winningNumbers) {

    }

    private static void validateBonusNumber(String bonusNumber) {

    }
}
