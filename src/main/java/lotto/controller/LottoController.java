package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoAnalyzer;
import lotto.domain.LottoMachine;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.dto.WinningStatisticResponse;
import lotto.util.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = createMoney();
        List<Lotto> lottos = LottoMachine.issueLottoByAmount(money);
        outputView.printLottos(lottos);

        WinningLotto winningLotto = createWinningLotto();
        LottoAnalyzer lottoAnalyzer = createLottoMachine(winningLotto);

        Map<Rank, Integer> statistics = lottoAnalyzer.analyze(lottos);
        outputView.printWinningStatistics(WinningStatisticResponse.from(statistics));

        double profitRate = lottoAnalyzer.calculateProfitRate(lottos, money);

    }

    private Money createMoney() {
        while (true) {
            try {
                String input = Parser.removeAllSpaces(inputView.inputPurchaseAmount());
                return Money.from(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto createWinningLotto() {
        while (true) {
            try {
                String input = Parser.removeAllSpaces(inputView.inputWinningNumbers());
                return WinningLotto.from(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private LottoAnalyzer createLottoMachine(WinningLotto winningLotto) {
        while (true) {
            try {
                String input = Parser.removeAllSpaces(inputView.inputBonusNumber());
                return LottoAnalyzer.from(winningLotto, input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
