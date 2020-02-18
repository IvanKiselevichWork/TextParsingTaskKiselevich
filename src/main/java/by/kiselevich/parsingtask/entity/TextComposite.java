package by.kiselevich.parsingtask.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TextComposite implements TextComponent {

    private List<TextComponent> textComponents;
    private TextComponentType textComponentType;

    public TextComposite(TextComponentType textComponentType) {
        textComponents = new LinkedList<>();
        this.textComponentType = textComponentType;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponent.add(textComponent);
    }

    @Override
    public Object getTextComponent(int index) {
        return textComponents.get(index);
    }

    @Override
    public void print() {
        for (TextComponent textComponent : textComponents) {
            textComponent.print();
        }
    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public TextComponentType getType() {
        return textComponentType;
    }

    @Override
    public Iterator<TextComponent> getIterator() {
        return textComponents.iterator();
    }
}
