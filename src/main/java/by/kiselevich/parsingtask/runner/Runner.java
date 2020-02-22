package by.kiselevich.parsingtask.runner;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.entity.TextComponentType;
import by.kiselevich.parsingtask.exception.SortException;
import by.kiselevich.parsingtask.exception.TextParseException;
import by.kiselevich.parsingtask.parser.*;
import by.kiselevich.parsingtask.sorter.ParagraphsBySentenceCountSorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

    private static final Logger LOG = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        String sourceText = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
                "\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "\tBye.\n";

        AbstractParser expressionParser = new ExpressionToValueParser();
        AbstractParser textParser = new TextComponentParser(TextComponentType.TEXT);
        AbstractParser paragraphParser = new TextComponentParser(TextComponentType.PARAGRAPH);
        AbstractParser sentenceParser = new TextComponentParser(TextComponentType.SENTENCE);
        AbstractParser lexemeParser = new TextComponentParser(TextComponentType.LEXEME);
        AbstractParser wordParser = new TextComponentParser(TextComponentType.WORD);
        AbstractParser symbolParser = new SymbolToTextLeafParser();

        expressionParser.setNextParser(textParser);
        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        try {
            TextComponent textComponent = expressionParser.parse(sourceText);

            LOG.trace("---------Source:");
            LOG.trace("\n{}", sourceText);
            LOG.trace("---------After replacing and parsing");
            LOG.trace("\n{}", textComponent);
            ParagraphsBySentenceCountSorter sorter = new ParagraphsBySentenceCountSorter();
            sorter.sortParagraphsBySentenceCount(textComponent);
            LOG.trace("---------After sorting paragraphs by sentence count");
            LOG.trace("\n{}", textComponent);

        } catch (TextParseException | SortException e) {
            LOG.error(e);
        }
    }
}
