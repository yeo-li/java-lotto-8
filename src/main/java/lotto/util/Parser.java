package lotto.util;

public class Parser {

    public static String[] parseInput(String input) {
        return removeAllSpaces(input).split(",", -1);
    }

    public static String removeAllSpaces(String input) {
        return input.replaceAll(" ", "");
    }
}

