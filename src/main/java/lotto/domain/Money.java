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
        shouldThrowExceptionWhenEmptyInput(input);
        shouldThrowExceptionWhenOutOfIntegerRange(input);
        shouldThrowExceptionWhenNotDivisibleByThousand(input);
        shouldThrowExceptionWhenZeroAmount(input);
    }

    private static void shouldThrowExceptionWhenNonNumeric(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(MoneyErrorMessage.NOT_NUMERIC.text());
            }
        }
    }

    private static void shouldThrowExceptionWhenOutOfIntegerRange(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MoneyErrorMessage.OUT_OF_INTEGER_RANGE.text());
        }
    }

    private static void shouldThrowExceptionWhenNotDivisibleByThousand(String input) {
        int amount = Integer.parseInt(input);
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException(MoneyErrorMessage.NOT_DIVISIBLE_BY_THOUSAND.text());
        }
    }

    private static void shouldThrowExceptionWhenZeroAmount(String input) {
        int amount = Integer.parseInt(input);
        if (amount == 0) {
            throw new IllegalArgumentException(MoneyErrorMessage.ZERO_AMOUNT.text());
        }
    }

    private static void shouldThrowExceptionWhenEmptyInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(MoneyErrorMessage.EMPTY_INPUT.text());
        }
    }


    public int getAmount() {
        return this.amount;
    }

}
