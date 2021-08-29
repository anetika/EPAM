package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.Leaf;
import edu.epam.handling.exception.CompositeException;
import edu.epam.handling.parser.CompositeParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser implements CompositeParser {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void setNextParser(CompositeParser parser) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void processData(String text, TextComponent component) throws CompositeException {
        if (component.getType() == ComponentType.WORD) {
            char[] letters = text.toCharArray();
            for (var letter : letters) {
                Leaf leaf = new Leaf(ComponentType.LETTER, letter);
                component.add(leaf);
            }
            logger.info("Word was parsed");
        } else {
            throw new CompositeException("Something wrong with text");
        }
    }
}
