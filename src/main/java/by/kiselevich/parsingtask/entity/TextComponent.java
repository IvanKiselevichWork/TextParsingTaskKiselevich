package by.kiselevich.parsingtask.entity;

import java.util.Iterator;

public interface TextComponent {

    void addTextComponent(TextComponent textComponent);

    Object getTextComponent(int index);

    String toString();

    void remove(TextComponent textComponent);

    TextComponentType getType();

    Iterator<TextComponent> getComponentIterator();

    int componentsCount();

    void clear();
}
