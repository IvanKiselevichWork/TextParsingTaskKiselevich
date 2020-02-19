package by.kiselevich.parsingtask.sorter;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.entity.TextComponentType;
import by.kiselevich.parsingtask.exception.SortException;

import java.util.*;

public class ParagraphsBySentenceCountSorter {

    private static final String INVALID_ARGUMENT_EXCEPTION_MESSAGE = "Invalid argument";

    public void sortParagraphsBySentenceCount(TextComponent textComponent) throws SortException{
        if (textComponent == null || textComponent.getType() != TextComponentType.TEXT) {
            throw new SortException(INVALID_ARGUMENT_EXCEPTION_MESSAGE);
        }

        Comparator<TextComponent> componentComparator = Comparator.comparing(TextComponent::componentsCount);

        List<TextComponent> paragraphs = new ArrayList<>();
        Map<TextComponent, String> paragraphToDelimiterMap = new HashMap<>();
        Iterator<TextComponent> paragraphIterator = textComponent.getComponentIterator();
        Iterator<String> paragraphDelimiterIterator = textComponent.getComponentDelimiterIterator();
        TextComponent tempComponent;
        while (paragraphIterator.hasNext()) {
            tempComponent = paragraphIterator.next();
            paragraphs.add(tempComponent);
            paragraphToDelimiterMap.put(tempComponent, paragraphDelimiterIterator.next());
        }

        paragraphs.sort(componentComparator);

        textComponent.clear();

        for (TextComponent paragraph : paragraphs) {
            textComponent.addTextComponent(paragraph);
            textComponent.addComponentDelimiter(paragraphToDelimiterMap.get(paragraph));
        }

    }
}
