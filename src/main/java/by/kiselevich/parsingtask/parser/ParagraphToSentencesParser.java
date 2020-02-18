package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponentType;

public class ParagraphToSentencesParser extends AbstractParser {

    public ParagraphToSentencesParser() {
        regex = "(?m)([^\\.!?]+)([\\.!?]+)";
        textComponentType = TextComponentType.PARAGRAPH;
    }

}
