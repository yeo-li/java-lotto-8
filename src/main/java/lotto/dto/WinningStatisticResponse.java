package lotto.dto;

import java.util.Map;
import lotto.domain.Rank;

public record WinningStatisticResponse(
    int firstCount,
    int secondCount,
    int thirdCount,
    int fourthCount,
    int fifthCount
) {

    public static WinningStatisticResponse from(Map<Rank, Integer> statistic) {
        int firstCount = statistic.getOrDefault(Rank.FIRST, 0);
        int secondCount = statistic.getOrDefault(Rank.SECOND, 0);
        int thirdCount = statistic.getOrDefault(Rank.THIRD, 0);
        int fourthCount = statistic.getOrDefault(Rank.FOURTH, 0);
        int fifthCount = statistic.getOrDefault(Rank.FIFTH, 0);

        return new WinningStatisticResponse(
            firstCount,
            secondCount,
            thirdCount,
            fourthCount,
            fifthCount
        );
    }
}
