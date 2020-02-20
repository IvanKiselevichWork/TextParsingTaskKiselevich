package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponentType;

public class TextComponentParser extends AbstractParser {
    public TextComponentParser(TextComponentType textComponentType) {
        this.textComponentType = textComponentType;
        this.regex = textComponentType.getRegex();
    }
}
