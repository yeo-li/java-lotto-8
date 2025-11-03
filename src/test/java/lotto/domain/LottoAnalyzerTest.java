package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import lotto.exception.LottoAnalyzerErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoAnalyzerTest {

    @Nested
    @DisplayName("from() 테스트")
    class FromTest {

        @Test
        @DisplayName("변환 성공")
        void 변환_성공() {
            // given
            WinningLotto winningNumbers = WinningLotto.from("10,30,20,11,45,1");
            String bonusNumber = "3";

            // when
            LottoAnalyzer actual = LottoAnalyzer.from(winningNumbers, bonusNumber);

            // then
            List<Integer> expectedWinningNumbers = List.of(1, 10, 11, 20, 30, 45);
            int expectedBonusNumber = 3;

            assertThat(actual.getWinningNumbers().getLotto().getNumbers()).isEqualTo(
                expectedWinningNumbers);
            assertThat(actual.getBonusNumber()).isEqualTo(expectedBonusNumber);
        }

        @Test
        @DisplayName("숫자 외 다른 문자가 포함되어 있는 경우 예외 발생")
        void 숫자_외_다른_문자가_포함되어_있는_경우_예외_발생() {
            // given
            WinningLotto winningNumbers = WinningLotto.from("10,30,20,11,45,1");
            String bonusNumber = "3a";

            // when & then
            assertThatThrownBy(() -> LottoAnalyzer.from(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoAnalyzerErrorMessage.INVALID_CHARACTER.text());
        }

        @Test
        @DisplayName("보너스 번호가 1 미만, 45 초과인 경우 예외 발생")
        void 보너스_번호가_1_미만_45_초과인_경우_예외_발생() {
            // given
            WinningLotto winningNumbers = WinningLotto.from("10,30,20,11,45,1");
            String bonusNumber = "0";

            // when & then
            assertThatThrownBy(() -> LottoAnalyzer.from(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoAnalyzerErrorMessage.OUT_OF_RANGE.text());
        }

        @Test
        @DisplayName("당첨 번호와 중복되는 경우 예외 발생")
        void 당첨_번호와_중복되는_경우_예외_발생() {
            // given
            WinningLotto winningNumbers = WinningLotto.from("12,30,20,11,45,1");
            String bonusNumber = "45";

            // when & then
            assertThatThrownBy(() -> LottoAnalyzer.from(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoAnalyzerErrorMessage.DUPLICATED_NUMBER.text());
        }

    }

    @Nested
    @DisplayName("analyze() 테스트")
    class AnalyzeTest {

        @Test
        @DisplayName("여러 로또를 분석해 각 Rank별 개수를 정확히 반환")
        void 여러_로또를_분석해_통계를_반환() {
            // given
            WinningLotto winningNumbers = WinningLotto.from("1,2,3,4,5,6");
            String bonusNumber = "7";
            LottoAnalyzer analyzer = LottoAnalyzer.from(winningNumbers, bonusNumber);

            List<Lotto> lottos = List.of(
                Lotto.from("1,2,3,4,5,6"),      // 1등
                Lotto.from("1,2,3,4,5,7"),      // 2등
                Lotto.from("1,2,3,4,5,8"),      // 3등
                Lotto.from("1,2,3,4,9,10"),     // 4등
                Lotto.from("1,2,3,11,12,13"),   // 5등
                Lotto.from("8,9,10,11,12,13")   // 꽝
            );

            // when
            Map<Rank, Integer> statistics = analyzer.analyze(lottos);

            // then
            assertThat(statistics.get(Rank.FIRST)).isEqualTo(1);
            assertThat(statistics.get(Rank.SECOND)).isEqualTo(1);
            assertThat(statistics.get(Rank.THIRD)).isEqualTo(1);
            assertThat(statistics.get(Rank.FOURTH)).isEqualTo(1);
            assertThat(statistics.get(Rank.FIFTH)).isEqualTo(1);
            assertThat(statistics.get(Rank.MISS)).isEqualTo(1);
        }

        @Test
        @DisplayName("로또가 한 장도 없으면 빈 Map 반환")
        void 로또가_한장도_없으면_빈_Map_반환() {
            // given
            WinningLotto winningNumbers = WinningLotto.from("1,2,3,4,5,6");
            String bonusNumber = "7";
            LottoAnalyzer analyzer = LottoAnalyzer.from(winningNumbers, bonusNumber);

            List<Lotto> lottos = List.of();

            // when
            Map<Rank, Integer> actual = analyzer.analyze(lottos);

            // then
            int expected = 0;
            assertThat(actual.size()).isEqualTo(0);
        }
    }
}