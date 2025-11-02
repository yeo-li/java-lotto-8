package lotto.controller;

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
        WinningLotto winningLotto = createWinningLotto();
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
}
