package lotto.controller;

import java.util.List;
import lotto.Lotto;
import lotto.domain.LottoAnalyzer;
import lotto.domain.LottoMachine;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.util.Parser;
import lotto.view.InputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        Money money = createMoney();
        List<Lotto> lottos = LottoMachine.issueLottoByAmount(money);
        
        WinningLotto winningLotto = createWinningLotto();
        LottoAnalyzer lottoAnalyzer = createLottoMachine(winningLotto);

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
