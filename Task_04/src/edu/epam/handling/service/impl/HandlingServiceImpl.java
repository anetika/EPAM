package edu.epam.handling.service.impl;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.CompositeException;
import edu.epam.handling.service.HandlingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class HandlingServiceImpl implements HandlingService {
    private static Logger logger = LogManager.getLogger();
    private static final String vowels = "eyuioa";

    @Override
    public TextComponent paragraphSort(TextComponent component) {
        List<TextComponent> paragraphs = component.getAllComponents();
        TextComponent newText = new TextComposite(ComponentType.TEXT);
        newText.addAll(paragraphs.stream().sorted(Comparator.comparingInt(TextComponent::getComponentAmount)).collect(Collectors.toList()));
        logger.info("Paragraphs were sorted by amount of sentences");
        return newText;
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComponent component) throws CompositeException {
        List<TextComponent> paragraphs = component.getAllComponents();
        int maxLength = 0;
        List<TextComponent> result = new ArrayList<>();
        for (var paragraph : paragraphs){
            List<TextComponent> sentences = paragraph.getAllComponents();
            paragraph.clear();
            for (var sentence : sentences){
                List<TextComponent> words = receiveWordsFromSentence(sentence);
                for (var word : words){
                    if (word.getComponentAmount() > maxLength){
                        maxLength = word.getComponentAmount();
                        result.clear();
                        result.add(sentence);
                    }else if (word.getComponentAmount() == maxLength){
                        if (!result.contains(sentence)){
                            result.add(sentence);
                        }
                    }
                }
            }
        }
        logger.info("All sentences with longest word were returned");
        return result;
    }

    @Override
    public void deleteSentences(TextComponent component, int amount) throws CompositeException {
        List<TextComponent> paragraphs = component.getAllComponents();
        for (var paragraph : paragraphs){
            List<TextComponent> sentences = paragraph.getAllComponents();
            paragraph.clear();
            for (var sentence : sentences){
                List<TextComponent> words = receiveWordsFromSentence(sentence);
                if (words.size() >= amount){
                    paragraph.add(sentence);
                }
            }
        }
        logger.info("All sentences with amount of words less than " + amount + " were deleted");
    }

    @Override
    public HashMap<String, Integer> findSimilarWords(TextComponent component) throws CompositeException {
        HashMap<String, Integer> map = new HashMap<>();
        List<TextComponent> paragraphs = component.getAllComponents();
        for (var paragraph : paragraphs){
            List<TextComponent> sentences = paragraph.getAllComponents();
            for (var sentence : sentences){
                List<TextComponent> words = receiveWordsFromSentence(sentence);
                for (var word : words){
                    String wordToString = word.toString().toLowerCase();
                    if (map.containsKey(wordToString)) {
                        Integer oldValue = map.get(wordToString);
                        map.replace(wordToString, oldValue, oldValue + 1);
                    }else{
                        map.put(wordToString, 1);
                    }
                }
            }
        }
        logger.info("All similar words were returned");
        return map;
    }

    @Override
    public int amountOfVowels(TextComponent component) throws CompositeException {
        int amount = 0;
        List<TextComponent> words = receiveWordsFromSentence(component);
        for (var word : words){
            List<TextComponent> letters = word.getAllComponents();
            for (var letter : letters){
                if (vowels.contains(letter.toString().toLowerCase())){
                    amount++;
                }
            }
        }
        logger.info("Amount of vowels was returned");
        return amount;
    }

    @Override
    public int amountOfConsonants(TextComponent component) throws CompositeException {
        int amount = 0;
        List<TextComponent> words = receiveWordsFromSentence(component);
        for (var word : words){
            List<TextComponent> letters = word.getAllComponents();
            for (var letter : letters){
                if (!vowels.contains(letter.toString().toLowerCase())){
                    amount++;
                }
            }
        }
        logger.info("Amount of consonants was returned");
        return amount;
    }

    private List<TextComponent> receiveWordsFromSentence(TextComponent component) throws CompositeException {
        List<TextComponent> words = new ArrayList<>();
        List<TextComponent> lexemes = component.getAllComponents();
        for (var lexeme : lexemes){
            List<TextComponent> components = lexeme.getAllComponents();
            for (var c : components){
                if (c.getType() == ComponentType.WORD){
                    words.add(c);
                }
            }
        }
        logger.info("All words from sentence were returned");
        return words;
    }
}
