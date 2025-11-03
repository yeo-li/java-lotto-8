package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.enums.LottoConstant;

public class LottoMachine {

    public static List<Lotto> issueLottoByAmount(Money money) {
        int ticketCount = money.getAmount() / LottoConstant.LOTTO_PRICE;

        List<Lotto> issuedLotto = new ArrayList<>();
        while (ticketCount-- > 0) {
            issuedLotto.add(generateLotto());
        }

        return issuedLotto;
    }

    private static Lotto generateLotto() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(
            LottoConstant.LOTTO_NUMBER_MIN,
            LottoConstant.LOTTO_NUMBER_MAX,
            LottoConstant.LOTTO_NUMBER_COUNT
        );
        return new Lotto(randomNumbers);
    }
}
