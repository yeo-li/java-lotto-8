package lotto.view;

import java.util.List;
import lotto.domain.Lotto;

public class OutputView {

    private static final String PURCHASED_LOTTO_MESSAGE = "개를 구매했습니다.";

    public void printLottos(List<Lotto> lottos) {
        System.out.printf(lottos.size() + PURCHASED_LOTTO_MESSAGE);
        for (Lotto lotto : lottos) {
            System.out.println(lotto.format());
        }
    }
}
