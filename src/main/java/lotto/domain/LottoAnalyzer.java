package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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
        if (bonusNumber.isBlank()) {
            throw new IllegalArgumentException(LottoAnalyzerErrorMessage.INVALID_CHARACTER.text());
        }
        for (char character : bonusNumber.toCharArray()) {
            if (!Character.isDigit(character)) {
                throw new IllegalArgumentException(
                    LottoAnalyzerErrorMessage.INVALID_CHARACTER.text());
            }
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

    public Map<Rank, Integer> analyze(List<Lotto> lottos) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatchingNumbers(winningNumbers.lotto());
            boolean matchBonus = lotto.contains(bonusNumber);
            Rank rank = Rank.valueOf(matchCount, matchBonus);
            statistics.merge(rank, 1, Integer::sum);
        }

        return statistics;
    }

    public double calculateProfitRate(List<Lotto> lottos, Money money) {
        long totalPrize = calculateTotalPrize(lottos);
        return (double) totalPrize / money.getAmount() * 100.0;
    }

    private long calculateTotalPrize(List<Lotto> lottos) {
        Map<Rank, Integer> statistics = this.analyze(lottos);
        return statistics.entrySet().stream()
            .mapToLong(entry -> (long) entry.getKey().prize() * entry.getValue())
            .sum();
    }

    public WinningLotto getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
