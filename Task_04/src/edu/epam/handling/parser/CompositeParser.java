package edu.epam.handling.parser;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.exception.CompositeException;

public interface CompositeParser {
    void setNextParser(CompositeParser parser);
    void processData(String text, TextComponent component) throws CompositeException;
}
