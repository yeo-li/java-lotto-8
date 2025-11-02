package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.MoneyErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Nested
    @DisplayName("from() 테스트")
    class FromTest {

        @Test
        @DisplayName("변환 성공")
        void 변환_성공() {
            // given
            String input = "1000";

            // when
            Money actual = Money.from(input);

            // then
            int expected = 1000;
            assertThat(actual.getAmount()).isEqualTo(expected);
        }

        @Test
        @DisplayName("금액이 음수인 경우 예외 발생")
        void 금액이_음수인_경우_예외_발생() {
            // given
            String input = "-1000";

            // when & then
            assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.NEGATIVE_AMOUNT.text());
        }

        @Test
        @DisplayName("금액에 숫자를 제외한 다른 값이 있는 경우 예외 발생")
        void 금액에_숫자를_제외한_다른_값이_있는_경우_예외_발생() {
            // given
            String input = "1,000";

            // when & then
            assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.NOT_NUMERIC.text());
        }
    }

}