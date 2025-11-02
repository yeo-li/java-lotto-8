package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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


    }
}