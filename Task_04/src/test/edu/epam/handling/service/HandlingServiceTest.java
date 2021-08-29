package test.edu.epam.handling.service;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.CompositeException;
import edu.epam.handling.parser.impl.*;
import edu.epam.handling.reader.CompositeReader;
import edu.epam.handling.service.HandlingService;
import edu.epam.handling.service.impl.HandlingServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class HandlingServiceTest {
    private HandlingService service;
    private CompositeReader reader;
    private TextComponent root;
    private TextParser textParser;
    @BeforeClass
    public void setUp(){
        service = new HandlingServiceImpl();
        reader = new CompositeReader();
        root = new TextComposite(ComponentType.TEXT);

        textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);

    }
    @AfterClass
    public void tearDown(){
        service = null;
        reader = null;
        root = null;
    }
    private void process(String text) throws CompositeException {
        textParser.processData(text, root);
    }
    @Test
    public void sortParagraphsTestTrue() throws CompositeException {
        String text = reader.read("resources/organs.txt");
        process(text);
        TextComponent actual = service.paragraphSort(root);
        String expected = reader.read("resources/organs_correct.txt");
        assertEquals(actual.toString(), expected);
    }

    @Test
    public void findSentencesWithLongestWordTestTrue() throws CompositeException {
        String text = reader.read("resources/sentences.txt");
        process(text);
        List<TextComponent> actual = service.findSentencesWithLongestWord(root);
        String expected = "To fully understand and appreciate these accomplishments, let's take at some of the most well-known parts of the human body! ";
        assertEquals(actual.get(0).toString(), expected);
    }

    @Test
    public void deleteSentencesTestTrue() throws CompositeException {
        String text = reader.read("resources/sentences.txt");
        process(text);
        service.deleteSentences(root, 21);
        String expected = reader.read("resources/sentences_correct.txt");
        assertEquals(root.toString(), expected);
    }

    @Test
    public void findSimilarWordsTestTrue() throws CompositeException {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("hello", 4);
        map.put("world", 2);
        map.put("alex", 1);
        map.put("aleksandr", 1);
        String text = reader.read("resources/words.txt");
        process(text);
        HashMap<String, Integer> actual = service.findSimilarWords(root);
        assertEquals(actual, map);
    }

    @Test
    public void amountOfVowelsTestTrue() throws CompositeException {
        String text = reader.read("resources/sentence.txt");
        process(text);
        int actual = service.amountOfVowels(root.getComponent(0).getComponent(0));
        int expected = 22;
        assertEquals(actual, expected);
    }

    @Test
    public void amountOfConsonantsTestTrue() throws CompositeException {
        String text = reader.read("resources/sentence.txt");
        process(text);
        int actual = service.amountOfConsonants(root.getComponent(0).getComponent(0));
        int expected = 23;
        assertEquals(actual, expected);
    }
}
