package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.Lotto;
import lotto.exception.LottoMachineErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

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
            LottoMachine actual = LottoMachine.from(winningNumbers, bonusNumber);

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
            assertThatThrownBy(() -> LottoMachine.from(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMachineErrorMessage.INVALID_CHARACTER.text());
        }

        @Test
        @DisplayName("보너스 번호가 1 미만, 45 초과인 경우 예외 발생")
        void 보너스_번호가_1_미만_45_초과인_경우_예외_발생() {
            // given
            WinningLotto winningNumbers = WinningLotto.from("10,30,20,11,45,1");
            String bonusNumber = "0";

            // when & then
            assertThatThrownBy(() -> LottoMachine.from(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMachineErrorMessage.OUT_OF_RANGE.text());
        }

        @Test
        @DisplayName("당첨 번호와 중복되는 경우 예외 발생")
        void 당첨_번호와_중복되는_경우_예외_발생() {
            // given
            WinningLotto winningNumbers = WinningLotto.from("12,30,20,11,45,1");
            String bonusNumber = "45";

            // when & then
            assertThatThrownBy(() -> LottoMachine.from(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMachineErrorMessage.DUPLICATED_NUMBER.text());
        }

    }

    @Nested
    @DisplayName("issueLottoByAmount() 테스트")
    class IssueLottoByAmountTest {

        @Test
        @DisplayName("정상 실행")
        void 정상_실행() {
            // given
            WinningLotto winningNumbers = WinningLotto.from("10,30,20,11,45,1");
            String bonusNumber = "3";
            Money money = Money.from("16000");

            // when
            LottoMachine lottoMachine = LottoMachine.from(winningNumbers, bonusNumber);
            List<Lotto> actual = lottoMachine.issueLottoByAmount(money);

            // then
            int expected = 16;
            assertThat(actual.size()).isEqualTo(expected);
        }
    }
}