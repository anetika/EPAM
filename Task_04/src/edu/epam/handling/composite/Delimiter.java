package edu.epam.handling.composite;

public enum Delimiter {
    ENTER("\n"), SPACE(" "), SENTENCE_DELIMITER("[…!?.]");
    private String value;

    private Delimiter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
