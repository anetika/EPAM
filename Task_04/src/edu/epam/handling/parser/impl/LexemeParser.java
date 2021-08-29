package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.Leaf;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.CompositeException;
import edu.epam.handling.parser.CompositeParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class LexemeParser implements CompositeParser {
    private static Logger logger = LogManager.getLogger();
    private CompositeParser nextParser;
    private static final String PUNCTUATION_REGEX = "\\p{P}";

    @Override
    public void setNextParser(CompositeParser parser) {
        this.nextParser = parser;
    }

    @Override
    public void processData(String text, TextComponent component) throws CompositeException {
        if (component.getType() == ComponentType.LEXEME) {
            char[] symbols = text.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (var symbol : symbols) {
                if (Pattern.matches(PUNCTUATION_REGEX, String.valueOf(symbol))) {
                    if (!builder.isEmpty()) {
                        TextComposite composite = new TextComposite(ComponentType.WORD);
                        component.add(composite);
                        nextParser.processData(builder.toString(), composite);
                        builder.delete(0, builder.length());
                    }
                    component.add(new Leaf(ComponentType.PUNCTUATION, symbol));
                } else {
                    builder.append(symbol);
                }
            }
            if (!builder.isEmpty()) {
                TextComposite composite = new TextComposite(ComponentType.WORD);
                component.add(composite);
                nextParser.processData(builder.toString(), composite);
            }
            logger.info("Lexeme was parsed");
        } else {
            nextParser.processData(text, component);
        }
    }
}
