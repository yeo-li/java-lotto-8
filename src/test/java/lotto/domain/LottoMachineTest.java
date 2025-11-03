package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

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
            LottoAnalyzer lottoAnalyzer = LottoAnalyzer.from(winningNumbers, bonusNumber);
            List<Lotto> actual = LottoMachine.issueLottoByAmount(money);

            // then
            int expected = 16;
            assertThat(actual.size()).isEqualTo(expected);
        }
    }
}