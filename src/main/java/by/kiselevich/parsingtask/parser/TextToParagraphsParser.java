package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.entity.TextComponentType;
import by.kiselevich.parsingtask.exception.TextParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextToParagraphsParser extends AbstractParser {

    private String textBeginning = "";

    public TextToParagraphsParser() {
        regex = "(?m)(.+)(\n*\t*)";
        textComponentType = TextComponentType.TEXT;
    }

    @Override
    public TextComponent parse(String sourceText) throws TextParseException {
        String beginningRegex = "^[\\W\\D]*";
        Pattern pattern = Pattern.compile(beginningRegex);
        Matcher matcher = pattern.matcher(sourceText);
        if(matcher.find()) {
            sourceText = sourceText.substring(matcher.end());
            textBeginning = matcher.group();
        }
        return super.parse(sourceText);
    }

    @Override
    public String toString() {
        return textBeginning + super.toString();
    }
}
