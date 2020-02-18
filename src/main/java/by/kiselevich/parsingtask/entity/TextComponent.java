package by.kiselevich.parsingtask.entity;

import java.util.Iterator;

public interface TextComponent {

    void add(TextComponent textComponent);

    Object getTextComponent(int index);

    void print();

    void remove(TextComponent textComponent);

    TextComponentType getType();

    Iterator<TextComponent> getIterator();
}
