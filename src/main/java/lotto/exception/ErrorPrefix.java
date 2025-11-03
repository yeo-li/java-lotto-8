package lotto.exception;

public enum ErrorPrefix {
    PREFIX("[ERROR] ");

    private final String prefix;

    ErrorPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
