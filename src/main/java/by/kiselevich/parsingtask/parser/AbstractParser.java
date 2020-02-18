package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.entity.TextComponentType;
import by.kiselevich.parsingtask.entity.TextComposite;
import by.kiselevich.parsingtask.exception.TextParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractParser {

    private static final int TEXT_COMPONENT_GROUP_INDEX = 1;
    private static final int COMPONENT_DELIMITER_GROUP_INDEX = 2;

    protected AbstractParser nextParser;
    protected TextComponentType textComponentType;
    protected String regex;

    public TextComponent parse(String sourceText) throws TextParseException {
        TextComponent textComponent = new TextComposite(textComponentType);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceText);
        while (matcher.find()) {
            textComponent.addTextComponent(nextParser.parse(matcher.group(TEXT_COMPONENT_GROUP_INDEX)));
            textComponent.addComponentDelimiter(matcher.group(COMPONENT_DELIMITER_GROUP_INDEX));
        }
        return textComponent;
    }

    public void setNextParser(AbstractParser parser) {
        this.nextParser = parser;
    }
}
