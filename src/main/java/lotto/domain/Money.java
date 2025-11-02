package lotto.domain;

public class Money {

    private final int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money from(String input) {
        try {
            validate();
            return new Money(0);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void validate() {

    }

    public int getAmount() {
        return this.amount;
    }

}
