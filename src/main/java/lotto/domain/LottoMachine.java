package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public static List<Lotto> issueLottoByAmount(Money money) {
        int ticketCount = money.getAmount() / LOTTO_PRICE;

        List<Lotto> issuedLotto = new ArrayList<>();
        while (ticketCount-- > 0) {
            issuedLotto.add(generateLotto());
        }

        return issuedLotto;
    }

    private static Lotto generateLotto() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(
            LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBER_COUNT
        );
        return new Lotto(randomNumbers);
    }
}
