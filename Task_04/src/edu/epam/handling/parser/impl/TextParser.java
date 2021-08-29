package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.Delimiter;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.CompositeException;
import edu.epam.handling.parser.CompositeParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser implements CompositeParser {
    private static Logger logger = LogManager.getLogger();
    private CompositeParser nextParser;

    @Override
    public void setNextParser(CompositeParser parser) {
        this.nextParser = parser;
    }

    @Override
    public void processData(String text, TextComponent component) throws CompositeException {
        if (component.getType() == ComponentType.TEXT) {
            String[] paragraphs = text.split(Delimiter.ENTER.getValue());
            for (var paragraph : paragraphs) {
                if (!paragraph.isEmpty()) {
                    TextComposite composite = new TextComposite(ComponentType.PARAGRAPH);
                    component.add(composite);
                    nextParser.processData(paragraph, composite);
                }
            }
            logger.info("Text was parsed");
        } else {
            nextParser.processData(text, component);
        }
    }
}
