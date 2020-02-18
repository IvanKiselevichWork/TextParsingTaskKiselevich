package by.kiselevich.parsingtask.entity;

import java.util.Iterator;

public class TextLeaf implements TextComponent {

    private TextComponentType textComponentType = TextComponentType.SYMBOL;
    private Character character;

    public TextLeaf(Character character) {
        this.character = character;
    }

    @Override
    public void addTextComponent(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addComponentDelimiter(String string) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getTextComponent(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }

    @Override
    public void remove(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextComponentType getType() {
        return textComponentType;
    }

    @Override
    public Iterator<TextComponent> getIterator() {
        throw new UnsupportedOperationException();
    }
}
