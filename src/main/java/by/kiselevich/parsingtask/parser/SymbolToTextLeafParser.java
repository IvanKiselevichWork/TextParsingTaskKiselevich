package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.entity.TextLeaf;
import by.kiselevich.parsingtask.exception.TextParseException;

public class SymbolToTextLeafParser extends AbstractParser {

    private static final int FIRST_CHARACTER_INDEX = 0;
    private static final int MINIMUM_REQUIRED_STRING_LENGTH = 1;

    public SymbolToTextLeafParser() {
        regex = null;
        textComponentType = null;
        nextParser = null;
    }

    @Override
    public TextComponent parse(String character) throws TextParseException {
        if (character == null || character.length() < MINIMUM_REQUIRED_STRING_LENGTH) {
            throw new TextParseException();
        }
        return new TextLeaf(character.charAt(FIRST_CHARACTER_INDEX));
    }
}
