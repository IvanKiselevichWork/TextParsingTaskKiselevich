package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponentType;

public class WordToSymbolsParser extends AbstractParser {

    public WordToSymbolsParser() {
        regex = "(?m)(\\w+?)(\\W*)";
        textComponentType = TextComponentType.WORD;
    }

}
