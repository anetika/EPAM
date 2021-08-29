package edu.epam.handling.composite.impl;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.Delimiter;
import edu.epam.handling.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> componentList;
    private ComponentType type;

    public TextComposite(ComponentType type){
        this.type = type;
        componentList = new ArrayList<>();
    }

    @Override
    public void add(TextComponent component) {
        componentList.add(component);
    }

    @Override
    public void add(int index, TextComponent component) {
        componentList.add(index, component);
    }

    @Override
    public void addAll(List<TextComponent> componentList) {
        this.componentList.addAll(componentList);
    }

    @Override
    public void remove(TextComponent component) {
        componentList.remove(component);
    }

    @Override
    public void clear() {
        componentList.clear();
    }

    @Override
    public int getComponentAmount() {
        return componentList.size();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public TextComponent getComponent(int index) {
        return componentList.get(index);
    }

    @Override
    public List<TextComponent> getAllComponents() {
        return List.copyOf(componentList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        switch (this.type) {
            case TEXT -> {
                for (var paragraph : componentList) {
                    builder.append(paragraph).append(Delimiter.ENTER.getValue());
                }
                builder.deleteCharAt(builder.length() - 1);
            }
            case PARAGRAPH -> {
                for (var sentence : componentList) {
                    builder.append(sentence);
                }
                builder.deleteCharAt(builder.length() - 1);
            }
            case SENTENCE -> {
                for (var lexeme : componentList) {
                    builder.append(lexeme).append(Delimiter.SPACE.getValue());
                }
            }
            default -> {
                for (var component : componentList) {
                    builder.append(component);
                }
            }
        }
        return builder.toString();
    }
}
