package controller;

import lotto.domain.Money;
import lotto.view.InputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        Money money = inputMoney();
    }

    private Money inputMoney() {
        while (true) {
            try {
                String input = inputView.inputPurchaseAmount();
                return Money.from(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
