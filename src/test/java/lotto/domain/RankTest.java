package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RankTest {

    @Nested
    @DisplayName("valueOf() 테스트")
    class ValueOfTest {

        @Test
        @DisplayName("6개 번호가 일치하고 보너스 번호가 일치하지 않으면 FIRST를 반환")
        void 여섯개_번호가_일치하고_보너스_번호가_일치하지_않으면_FIRST를_반환() {
            // given
            int count = 6;
            boolean bonus = false;

            // when
            Rank actual = Rank.valueOf(count, bonus);

            // then
            Rank expected = Rank.FIRST;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("5개 번호가 일치하고 보너스 번호가 일치하면 SECOND를 반환")
        void 다섯개_번호가_일치하고_보너스_번호가_일치하면_SECOND를_반환() {
            // given
            int count = 5;
            boolean bonus = true;

            // when
            Rank actual = Rank.valueOf(count, bonus);

            // then
            Rank expected = Rank.SECOND;
            assertThat(actual).isEqualTo(expected);
        }
    }
}