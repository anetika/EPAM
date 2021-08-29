package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.Delimiter;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.CompositeException;
import edu.epam.handling.parser.CompositeParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser implements CompositeParser {
    private static Logger logger = LogManager.getLogger();
    private CompositeParser nextParser;

    @Override
    public void setNextParser(CompositeParser parser) {
        this.nextParser = parser;
    }

    @Override
    public void processData(String text, TextComponent component) throws CompositeException {
        if (component.getType() == ComponentType.SENTENCE) {
            String[] lexemes = text.split(Delimiter.SPACE.getValue());
            for (var lexeme : lexemes) {
                if (!lexeme.isEmpty()) {
                    TextComposite composite = new TextComposite(ComponentType.LEXEME);
                    component.add(composite);
                    nextParser.processData(lexeme, composite);
                }
            }
            logger.info("Sentence was parsed");
        } else {
            nextParser.processData(text, component);
        }
    }
}
