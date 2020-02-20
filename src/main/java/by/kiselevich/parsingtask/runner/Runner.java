package by.kiselevich.parsingtask.runner;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.entity.TextComponentType;
import by.kiselevich.parsingtask.exception.SortException;
import by.kiselevich.parsingtask.exception.TextParseException;
import by.kiselevich.parsingtask.parser.*;
import by.kiselevich.parsingtask.sorter.ParagraphsBySentenceCountSorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;

public class Runner {

    private static final Logger LOG = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        String sourceText = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
                "\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "\tBye.\n";

        AbstractParser expressionParser = new ExpressionToValueParser();
        AbstractParser nextParser = expressionParser;
        AbstractParser parser;
        for (TextComponentType textComponentType : TextComponentType.values()) {
            if (textComponentType != TextComponentType.SYMBOL) {
                parser = new TextComponentParser(textComponentType);
            } else {
                parser = new SymbolToTextLeafParser();
            }
            nextParser.setNextParser(parser);
            nextParser = parser;
        }

        try {
            TextComponent textComponent = expressionParser.parse(sourceText);

            System.out.println("---------Source:");
            System.out.println(sourceText);
            System.out.println("---------After replacing and parsing");
            System.out.println(textComponent.toString());
            ParagraphsBySentenceCountSorter sorter = new ParagraphsBySentenceCountSorter();
            sorter.sortParagraphsBySentenceCount(textComponent);
            System.out.println("---------After sorting paragraphs by sentence count");
            System.out.println(textComponent.toString());

        } catch (TextParseException | SortException e) {
            LOG.error(e);
        }
    }
}
