package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.exception.LottoErrorMessage;
import lotto.util.Parser;

public record Lotto(List<Integer> numbers) {

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = Collections.unmodifiableList(sortedNumbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        shouldThrowExceptionWhenDuplicatedNumber(numbers);
    }

    private void shouldThrowExceptionWhenDuplicatedNumber(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(LottoErrorMessage.DUPLICATED_NUMBER.text());
        }
    }

    public static Lotto from(String input) {
        validateInput(input);

        List<Integer> parsedNumbers = Arrays.stream(Parser.parseInput(input))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        return new Lotto(parsedNumbers);
    }

    private static void validateInput(String input) {
        shouldThrowExceptionWhenEmptyInput(input);
        shouldThrowExceptionWhenInvalidCharacter(input);
        shouldThrowExceptionWhenContainsWhitespace(input);
        shouldThrowExceptionWhenOutOfRange(input);
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

    private static void shouldThrowExceptionWhenOutOfRange(String input) {
        List<Integer> parsedNumbers = null;
        try {
            parsedNumbers = Arrays.stream(Parser.parseInput(input))
                .map(Integer::parseInt)
                .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(LottoErrorMessage.OUT_OF_RANGE.text());
        }

        if (parsedNumbers.stream().anyMatch(number -> number < 1 || number > 45)) {
            throw new IllegalArgumentException(LottoErrorMessage.OUT_OF_RANGE.text());
        }
    }

    public int countMatchingNumbers(Lotto lotto) {
        long matchCount = this.numbers.stream()
            .filter(lotto.numbers()::contains)
            .count();

        return (int) matchCount;
    }

    public String format() {
        return numbers.toString();
    }

    public boolean contains(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    @Override
    public List<Integer> numbers() {
        return List.copyOf(numbers);
    }
}
