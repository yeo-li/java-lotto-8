package lotto;

import java.util.Arrays;
import java.util.List;
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
    }

    public static Lotto from(String input) {
        validateInput(input);

        List<Integer> parsedNumbers = Arrays.stream(Parser.parseInput(input))
            .map(Integer::parseInt)
            .toList();

        return new Lotto(parsedNumbers);
    }

    private static void validateInput(String input) {

    }

}
