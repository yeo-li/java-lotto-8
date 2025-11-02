package lotto;

import java.util.Arrays;
import java.util.List;
import lotto.exception.LottoErrorMessage;
import lotto.util.Parser;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        shouldThrowExceptionWhenOutOfRange(numbers);
    }

    private void shouldThrowExceptionWhenOutOfRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || 45 < number) {
                throw new IllegalArgumentException(LottoErrorMessage.OUT_OF_RANGE.text());
            }
        }
    }


    public static Lotto from(String input) {
        validateInput(input);

        List<Integer> parsedNumbers = Arrays.stream(Parser.parseInput(input))
            .map(Integer::parseInt)
            .toList();

        return new Lotto(parsedNumbers);
    }

    private static void validateInput(String input) {
        shouldThrowExceptionWhenEmptyInput(input);
        shouldThrowExceptionWhenInvalidCharacter(input);
        shouldThrowExceptionWhenContainsWhitespace(input);
    }

    private static void shouldThrowExceptionWhenInvalidCharacter(String input) {
        if (!input.matches("[0-9,]+")) {
            throw new IllegalArgumentException(LottoErrorMessage.INVALID_CHARACTER.text());
        }
    }

    private static void shouldThrowExceptionWhenEmptyInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(LottoErrorMessage.EMPTY_INPUT.text());
        }
    }

    private static void shouldThrowExceptionWhenContainsWhitespace(String input) {
        String[] parsedInput = Parser.parseInput(input);
        for (String number : parsedInput) {
            if (number.isBlank()) {
                throw new IllegalArgumentException(LottoErrorMessage.CONTAINS_WHITESPACE.text());
            }
        }
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }
}
