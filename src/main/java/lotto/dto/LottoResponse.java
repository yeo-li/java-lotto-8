package lotto.dto;

import lotto.domain.Lotto;

public record LottoResponse(
    String formattedNumbers
) {

    public static LottoResponse from(Lotto lotto) {
        return new LottoResponse(lotto.format());
    }
}
