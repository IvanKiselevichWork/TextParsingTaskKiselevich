package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.entity.TextComponentType;
import by.kiselevich.parsingtask.entity.TextComposite;
import by.kiselevich.parsingtask.exception.TextParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractParser {

    protected AbstractParser nextParser;
    protected TextComponentType textComponentType;
    protected String regex;

    public AbstractParser() {

    }

    public TextComponent parse(String sourceText) throws TextParseException {
        if (sourceText == null || nextParser == null) {
            throw new TextParseException();
        }
        TextComponent textComponent = new TextComposite(textComponentType);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceText);
        while (matcher.find()) {
            textComponent.addTextComponent(nextParser.parse(matcher.group()));
        }
        if (textComponent.componentsCount() == 0) {
            textComponent.addTextComponent(nextParser.parse(sourceText));
        }
        return textComponent;
    }

    public void setNextParser(AbstractParser parser) {
        this.nextParser = parser;
    }
}
