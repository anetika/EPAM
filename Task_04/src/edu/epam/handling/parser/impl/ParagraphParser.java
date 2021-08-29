package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.Delimiter;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.CompositeException;
import edu.epam.handling.parser.CompositeParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements CompositeParser {
    private static Logger logger = LogManager.getLogger();
    private CompositeParser nextParser;

    @Override
    public void setNextParser(CompositeParser parser) {
        this.nextParser = parser;
    }

    @Override
    public void processData(String text, TextComponent component) throws CompositeException {
        if (component.getType() == ComponentType.PARAGRAPH) {
            Pattern pattern = Pattern.compile(Delimiter.SENTENCE_DELIMITER.getValue());
            Matcher matcher = pattern.matcher(text);
            int index = 0;
            while (matcher.find()) {
                String sentence = text.substring(index, matcher.end());
                index = matcher.end();
                TextComposite composite = new TextComposite(ComponentType.SENTENCE);
                component.add(composite);
                nextParser.processData(sentence, composite);
            }
            logger.info("Paragraph was parsed");
        } else {
            nextParser.processData(text, component);
        }
    }
}
