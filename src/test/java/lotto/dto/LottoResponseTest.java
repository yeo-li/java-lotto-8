package lotto.dto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoResponseTest {

    @Nested
    @DisplayName("from() 테스트")
    class FromTest {

        @Test
        @DisplayName("Lotto를 LottoResponse로 변환하면 로또 번호가 포맷된 문자열로 저장")
        void from_로또를_LottoResponse로_변환하면_포맷된_문자열이_저장() {
            // given
            Lotto lotto = Lotto.from("1,2,3,4,5,6");

            // when
            LottoResponse response = LottoResponse.from(lotto);

            // then
            assertThat(response.formattedNumbers()).isEqualTo("[1, 2, 3, 4, 5, 6]");
        }
    }
}