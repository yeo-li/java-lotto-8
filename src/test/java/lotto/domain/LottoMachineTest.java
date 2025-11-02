package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
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
            String winningNumbers = "10,30,20,11,45,1";
            String bonusNumber = "3";

            // when
            LottoMachine actual = LottoMachine.from(winningNumbers, bonusNumber);

            // then
            List<Integer> expectedWinningNumbers = List.of(1, 10, 11, 20, 30, 45);
            int expectedBonusNumber = 3;

            assertThat(actual.getWinningNumbers().getNumbers()).isEqualTo(expectedWinningNumbers);
            assertThat(actual.getBonusNumber()).isEqualTo(expectedBonusNumber);
        }

        @Test
        @DisplayName("숫자, 컴마(,) 외 다른 문자가 포함되어 있는 경우 예외 발생")
        void 숫자_컴마_외_다른_문자가_포함되어_있는_경우_예외_발생() {
            // given
            String input = "1,2,3,4,5,A";
            String bonusNumber = "3";

            // when & then
            assertThatThrownBy(() -> LottoMachine.from(input, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMachineErrorMessage.INVALID_CHARACTER.text());
        }

        @Test
        @DisplayName("당첨 번호의 갯수가 6개가 아닌 경우 예외 발생")
        void 당첨_번호의_갯수가_6개가_아닌_경우_예외_발생() {
            // given
            String input = "1,2,3,4,5,6,7";
            String bonusNumber = "3";

            // when & then
            assertThatThrownBy(() -> LottoMachine.from(input, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMachineErrorMessage.INVALID_COUNT.text());
        }

        @Test
        @DisplayName("당첨 번호에 공백만 입력된 경우 예외 발생")
        void 당첨_번호에_공백만_입력된_경우_예외_발생() {
            // given
            String input = "";
            String bonusNumber = "3";

            // when & then
            assertThatThrownBy(() -> LottoMachine.from(input, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMachineErrorMessage.EMPTY_INPUT.text());
        }

        @Test
        @DisplayName("당첨 번호에 공백이 포함되어 있는 경우 예외 발생")
        void 당첨_번호에_공백이_포함되어_있는_경우_예외_발생() {
            // given
            String input = "1,2,3,,4,5";
            String bonusNumber = "3";

            // when & then
            assertThatThrownBy(() -> LottoMachine.from(input, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMachineErrorMessage.CONTAINS_WHITESPACE.text());
        }

        @Test
        @DisplayName("당첨 번호가 1 미만, 45 초과인 경우 예외 발생")
        void 로또_번호가_1_미만_45_초과인_경우_예외_발생() {
            // given
            String input = "0,3,4,5,6,46";
            String bonusNumber = "3";

            // when & then
            assertThatThrownBy(() -> LottoMachine.from(input, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMachineErrorMessage.OUT_OF_RANGE.text());
        }
    }
}