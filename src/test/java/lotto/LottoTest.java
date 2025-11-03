package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.Lotto;
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
        @DisplayName("변환 시 오름차순 정렬 성공")
        void 변환_시_오름차순_정렬_성공() {
            // given
            String input = "10,30,20,11,45,1";

            // when
            Lotto actual = Lotto.from(input);

            // then
            List<Integer> expected = List.of(1, 10, 11, 20, 30, 45);
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
        @DisplayName("로또 번호의 갯수가 6개가 아닌 경우 예외 발생")
        void 로또_번호의_갯수가_6개가_아닌_경우_예외_발생() {
            // given
            String input = "1,2,3,4,5,6,7";

            // when & then
            assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoErrorMessage.INVALID_COUNT.text());
        }

        @Test
        @DisplayName("로또 번호에 공백만 입력된 경우 예외 발생")
        void 로또_번호에_공백만_입력된_경우_예외_발생() {
            // given
            String input = "";

            // when & then
            assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoErrorMessage.EMPTY_INPUT.text());
        }

        @Test
        @DisplayName("로또 번호에 공백이 포함되어 있는 경우 예외 발생")
        void 로또_번호에_공백이_포함되어_있는_경우_예외_발생() {
            // given
            String input = "1,2,3,,4,5";

            // when & then
            assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoErrorMessage.CONTAINS_WHITESPACE.text());
        }

        @Test
        @DisplayName("로또 번호가 1 미만, 45 초과인 경우 예외 발생")
        void 로또_번호가_1_미만_45_초과인_경우_예외_발생() {
            // given
            String input = "1000000000000000000000000,3,4,5,6,46";

            // when & then
            assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoErrorMessage.OUT_OF_RANGE.text());
        }

        @Test
        @DisplayName("로또 번호의 숫자가 중복되는 경우 예외 발생")
        void 로또_번호의_숫자가_중복되는_경우_예외_발생() {
            // given
            String input = "1,2,3,4,4,5";

            // when & then
            assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoErrorMessage.DUPLICATED_NUMBER.text());
        }
    }

    @Nested
    @DisplayName("countMatchingNumbers() 테스트")
    class CountMatchingNumbersTest {

        @Test
        @DisplayName("0개가 겹치는 경우")
        void 영개가_겹치는_경우() {
            // given
            Lotto lotto1 = Lotto.from("1,2,3,4,5,6");
            Lotto lotto2 = Lotto.from("7,8,9,10,11,12");

            // when
            int actual = lotto1.countMatchingNumbers(lotto2);

            // then
            int expected = 0;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("1개가 겹치는 경우")
        void 한개가_겹치는_경우() {
            // given
            Lotto lotto1 = Lotto.from("1,2,3,4,5,6");
            Lotto lotto2 = Lotto.from("1,8,9,10,11,12");

            // when
            int actual = lotto1.countMatchingNumbers(lotto2);

            // then
            int expected = 1;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("2개가 겹치는 경우")
        void 두개가_겹치는_경우() {
            // given
            Lotto lotto1 = Lotto.from("1,2,3,4,5,6");
            Lotto lotto2 = Lotto.from("1,2,9,10,11,12");

            // when
            int actual = lotto1.countMatchingNumbers(lotto2);

            // then
            int expected = 2;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("3개가 겹치는 경우")
        void 세개가_겹치는_경우() {
            // given
            Lotto lotto1 = Lotto.from("1,2,3,4,5,6");
            Lotto lotto2 = Lotto.from("1,2,3,10,11,12");

            // when
            int actual = lotto1.countMatchingNumbers(lotto2);

            // then
            int expected = 3;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("4개가 겹치는 경우")
        void 네개가_겹치는_경우() {
            // given
            Lotto lotto1 = Lotto.from("1,2,3,4,5,6");
            Lotto lotto2 = Lotto.from("1,2,3,4,11,12");

            // when
            int actual = lotto1.countMatchingNumbers(lotto2);

            // then
            int expected = 4;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("5개가 겹치는 경우")
        void 다섯개가_겹치는_경우() {
            // given
            Lotto lotto1 = Lotto.from("1,2,3,4,5,6");
            Lotto lotto2 = Lotto.from("1,2,3,4,5,12");

            // when
            int actual = lotto1.countMatchingNumbers(lotto2);

            // then
            int expected = 5;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("6개가 겹치는 경우")
        void 여섯개가_겹치는_경우() {
            // given
            Lotto lotto1 = Lotto.from("1,2,3,4,5,6");
            Lotto lotto2 = Lotto.from("1,2,3,4,5,6");

            // when
            int actual = lotto1.countMatchingNumbers(lotto2);

            // then
            int expected = 6;
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("contains() 테스트")
    class ContainsTest {

        @Test
        @DisplayName("보너스 번호가 포함되어 있으면 true를 반환")
        void 보너스_번호가_포함되어_있으면_true를_반환() {
            // given
            Lotto lotto = Lotto.from("1,2,3,4,5,6");
            int bonusNumber = 6;

            // when
            boolean actual = lotto.contains(bonusNumber);

            // then
            boolean expected = true;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("보너스 번호가 포함되어 있지 않으면 false를 반환")
        void 보너스_번호가_포함되어_있지_않으면_false를_반환() {
            // given
            Lotto lotto = Lotto.from("1,2,3,4,5,6");
            int bonusNumber = 7;

            // when
            boolean actual = lotto.contains(bonusNumber);

            // then
            boolean expected = false;
            assertThat(actual).isEqualTo(expected);
        }
    }
}
