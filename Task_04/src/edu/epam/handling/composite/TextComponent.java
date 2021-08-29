package edu.epam.handling.composite;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    void add(int index, TextComponent component);
    void addAll(List<TextComponent> componentList);
    void remove(TextComponent component);
    void clear();
    int getComponentAmount();
    ComponentType getType();
    TextComponent getComponent(int index);
    List<TextComponent> getAllComponents();
}
