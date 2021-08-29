package edu.epam.handling.composite.impl;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.TextComponent;

import java.util.List;

public class Leaf implements TextComponent {
    private ComponentType type;
    private char value;

    public Leaf(ComponentType type, char value){
        this.type = type;
        this.value = value;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addAll(List<TextComponent> componentList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getComponentAmount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public TextComponent getComponent(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TextComponent> getAllComponents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
