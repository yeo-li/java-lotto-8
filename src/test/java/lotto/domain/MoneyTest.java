package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

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
            assertThat(actual).isEqualTo(expected);
        }
    }

}