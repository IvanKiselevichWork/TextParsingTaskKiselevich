package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponentType;

public class LexemeToWordParser extends AbstractParser {

    public LexemeToWordParser() {
        regex = "(?m)(\\w+)(\\W*)";
        textComponentType = TextComponentType.LEXEME;
    }

}
