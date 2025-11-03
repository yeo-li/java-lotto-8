package lotto.dto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
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

    @Test
    @DisplayName("Lotto 리스트를 LottoResponse 리스트로 변환하면 각각 포맷된 문자열로 저장")
    void from_Lotto_리스트를_LottoResponse_리스트로_변환하면_포맷된_문자열이_저장() {
        // given
        List<Lotto> lottos = List.of(
            Lotto.from("1,2,3,4,5,6"),
            Lotto.from("7,8,9,10,11,12")
        );

        // when
        List<LottoResponse> responses = LottoResponse.from(lottos);

        // then
        assertThat(responses.size()).isEqualTo(2);
        assertThat(responses.get(0).formattedNumbers()).isEqualTo("[1, 2, 3, 4, 5, 6]");
        assertThat(responses.get(1).formattedNumbers()).isEqualTo("[7, 8, 9, 10, 11, 12]");
    }
}