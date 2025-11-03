package lotto.dto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Map;
import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinningStatisticResponseTest {

    @Nested
    @DisplayName("from() 테스트")
    class FromTest {

        @Test
        @DisplayName("로또 통계 Map을 WinningStatisticResponse로 변환")
        void from_정상_변환() {
            Map<Rank, Integer> statistic = Map.of(
                Rank.FIRST, 1,
                Rank.THIRD, 2
            );

            WinningStatisticResponse response = WinningStatisticResponse.from(statistic);

            assertThat(response.firstCount()).isEqualTo(1);
            assertThat(response.thirdCount()).isEqualTo(2);
            assertThat(response.fourthCount()).isEqualTo(0);
        }
    }
}