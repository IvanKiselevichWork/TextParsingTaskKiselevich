package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponentType;

public class SentenceToLexemesParser extends AbstractParser {

    public SentenceToLexemesParser() {
        regex = "(?m)([^ ]+)( *)";
        textComponentType = TextComponentType.SENTENCE;
    }
}
