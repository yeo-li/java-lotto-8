package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.LottoErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    @DisplayName("from() 테스트")
    class FromTest {

        @Test
        @DisplayName("변환 성공")
        void 변환_성공() {
            // given
            String input = "1,2,3,4,5,6";

            // when
            Lotto actual = Lotto.from(input);

            // then
            List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
            assertThat(actual.getNumbers()).isEqualTo(expected);
        }

        @Test
        @DisplayName("숫자, 컴마(,) 외 다른 문자가 포함되어 있는 경우 예외 발생")
        void 숫자_컴마_외_다른_문자가_포함되어_있는_경우_예외_발생() {
            // given
            String input = "1,2,3,4,5,A";

            // when & then
            assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoErrorMessage.INVALID_CHARACTER.text());
        }

        @Test
        @DisplayName("당첨 번호의 갯수가 6개가 아닌 경우 예외 발생")
        void 당첨_번호의_갯수가_6개가_아닌_경우_예외_발생() {
            // given
            String input = "1,2,3,4,5,6,7";

            // when & then
            assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoErrorMessage.INVALID_COUNT.text());
        }
    }
}
