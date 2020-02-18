package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponentType;

public class TextToParagraphsParser extends AbstractParser {

    public TextToParagraphsParser() {
        regex = "(?m)(.+)(\n*\t*)";
        textComponentType = TextComponentType.TEXT;
    }

}
