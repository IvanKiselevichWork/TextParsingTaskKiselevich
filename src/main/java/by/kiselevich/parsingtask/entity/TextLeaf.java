package by.kiselevich.parsingtask.entity;

import java.util.Iterator;

public class TextLeaf implements TextComponent {

    private TextComponentType textComponentType = TextComponentType.SYMBOL;
    private Character value;

    public TextLeaf(Character value) {
        this.value = value;
    }

    @Override
    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getTextComponent(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print() {
        System.out.println(value);
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
