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
        @DisplayName("금액에 숫자를 제외한 다른 값이 있는 경우 예외 발생")
        void 금액에_숫자를_제외한_다른_값이_있는_경우_예외_발생() {
            // given
            String input = "1,000";

            // when & then
            assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.NOT_NUMERIC.text());
        }

        @Test
        @DisplayName("금액이 int 자료형의 범위를 초과하는 경우 예외 발생")
        void 금액이_int_자료형의_범위를_초과하는_경우_예외_발생() {
            // given
            String input = "10000000000000000000";

            // when & then
            assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.OUT_OF_INTEGER_RANGE.text());
        }

        @Test
        @DisplayName("금액이 1,000원 단위로 나누어 떨어지지 않는 경우 예외 발생")
        void 금액이_천원_단위로_나누어_떨어지지_않는_경우_예외_발생() {
            // given
            String input = "10001";

            // when & then
            assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.NOT_DIVISIBLE_BY_THOUSAND.text());
        }

        @Test
        @DisplayName("입력값이 0인 경우 예외 발생")
        void 입력값이_0인_경우_예외_발생() {
            // given
            String input = "0";

            // when & then
            assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.ZERO_AMOUNT.text());
        }

        @Test
        @DisplayName("입력값이 공백인 경우 예외 발생")
        void 입력값이_공백인_경우_예외_발생() {
            // given
            String input = "";

            // when & then
            assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.EMPTY_INPUT.text());
        }
    }
    
}