package by.kiselevich.parsingtask.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TextComposite implements TextComponent {

    private static final String EMPTY_STRING = "";

    private List<TextComponent> textComponents;
    private TextComponentType textComponentType;
    private List<String> componentDelimiters;

    public TextComposite(TextComponentType textComponentType) {
        textComponents = new LinkedList<>();
        componentDelimiters = new LinkedList<>();
        this.textComponentType = textComponentType;
    }

    @Override
    public void addTextComponent(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void addComponentDelimiter(String string) {
        componentDelimiters.add(string);
    }

    @Override
    public Object getTextComponent(int index) {
        return textComponents.get(index);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> delimiterIterator = componentDelimiters.iterator();
        String delimiter;
        for (TextComponent textComponent : textComponents) {
            delimiter = delimiterIterator.next();
            stringBuilder.append(textComponent.toString()).append(delimiter != null ? delimiter : EMPTY_STRING);
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
    public Iterator<String> getComponentDelimiterIterator() {
        return componentDelimiters.iterator();
    }

    @Override
    public int componentsCount() {
        return textComponents.size();
    }

    @Override
    public void clear() {
        textComponents.clear();
        componentDelimiters.clear();
    }
}
