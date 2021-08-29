package edu.epam.handling.service;


import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.exception.CompositeException;

import java.util.HashMap;
import java.util.List;

public interface HandlingService {
    TextComponent paragraphSort(TextComponent component);
    List<TextComponent> findSentencesWithLongestWord(TextComponent component) throws CompositeException;
    void deleteSentences(TextComponent component, int amount) throws CompositeException;
    HashMap<String, Integer> findSimilarWords(TextComponent component) throws CompositeException;
    int amountOfVowels(TextComponent component) throws CompositeException;
    int amountOfConsonants(TextComponent component) throws CompositeException;
}
