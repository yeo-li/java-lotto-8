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
        shouldThrowExceptionWhenNonNumeric(input);
    }

    private static void shouldThrowExceptionWhenNonNumeric(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(MoneyErrorMessage.NOT_NUMERIC.text());
            }
        }
    }

    public int getAmount() {
        return this.amount;
    }

}
