package by.kiselevich.parsingtask.entity;

import java.util.Iterator;

public interface TextComponent {

    void addTextComponent(TextComponent textComponent);

    void addComponentDelimiter(String string);

    Object getTextComponent(int index);

    String toString();

    void remove(TextComponent textComponent);

    TextComponentType getType();

    Iterator<TextComponent> getIterator();
}
