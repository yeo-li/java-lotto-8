package lotto.domain;

import lotto.exception.MoneyErrorMessage;

public class Money {

    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money from(String input) {
        try {
            validate(input);
            int parsedInput = Integer.parseInt(input);
            return new Money(parsedInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void validate(String input) {
        shouldThrowExceptionWhenNegativeAmount(input);
    }

    private static void shouldThrowExceptionWhenNegativeAmount(String input) {
        int parsedInput = Integer.parseInt(input);
        if (parsedInput < 0) {
            throw new IllegalArgumentException(MoneyErrorMessage.NEGATIVE_AMOUNT.text());
        }
    }

    public int getAmount() {
        return this.amount;
    }

}
