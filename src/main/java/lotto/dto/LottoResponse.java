package lotto.dto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;

public record LottoResponse(
    String formattedNumbers
) {

    public static LottoResponse from(Lotto lotto) {
        return new LottoResponse(lotto.format());
    }

    public static List<LottoResponse> from(List<Lotto> lottos) {
        List<LottoResponse> lottoResponses = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottoResponses.add(LottoResponse.from(lotto));
        }
        return lottoResponses;
    }
}
