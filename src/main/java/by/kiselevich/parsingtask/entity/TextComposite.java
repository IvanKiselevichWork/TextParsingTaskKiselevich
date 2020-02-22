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
    public void addTextComponent(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public Object getTextComponent(int index) {
        return textComponents.get(index);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent textComponent : textComponents) {
            stringBuilder.append(textComponent.toString());
        }
        return stringBuilder.toString();
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
    public Iterator<TextComponent> getComponentIterator() {
        return textComponents.iterator();
    }

    @Override
    public int componentsCount() {
        return textComponents.size();
    }

    @Override
    public void clear() {
        textComponents.clear();
    }
}
