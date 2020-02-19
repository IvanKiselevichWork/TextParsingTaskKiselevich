package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponentType;

public class LexemeToWordsParser extends AbstractParser {

    public LexemeToWordsParser() {
        regex = "(?m)(\\w+)(\\W*)";
        textComponentType = TextComponentType.LEXEME;
    }

}
