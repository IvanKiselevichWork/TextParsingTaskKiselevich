package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponentType;

public class WordToSymbolParser extends AbstractParser {

    public WordToSymbolParser() {
        regex = "(?m)(\\w+?)(\\W*)";
        textComponentType = TextComponentType.WORD;
    }

}
