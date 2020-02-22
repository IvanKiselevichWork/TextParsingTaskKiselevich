package by.kiselevich.parsingtask.runner;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.entity.TextComponentType;
import by.kiselevich.parsingtask.exception.SortException;
import by.kiselevich.parsingtask.exception.SourceTextReaderException;
import by.kiselevich.parsingtask.exception.TextParseException;
import by.kiselevich.parsingtask.parser.*;
import by.kiselevich.parsingtask.reader.SourceTextReader;
import by.kiselevich.parsingtask.sorter.ParagraphsBySentenceCountSorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class Runner {

    private static final String SOURCE_TEXT_FILENAME = "input.txt";
    private static final Logger LOG = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        try {
            File sourceTextFile = getFileFromResources(SOURCE_TEXT_FILENAME);

            SourceTextReader sourceTextReader = new SourceTextReader(sourceTextFile);

            String sourceText = sourceTextReader.getSourceText();

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

            TextComponent textComponent = expressionParser.parse(sourceText);
            LOG.trace("---------Source:");
            LOG.trace("\n{}", sourceText);
            LOG.trace("---------After replacing and parsing");
            LOG.trace("\n{}", textComponent);
            ParagraphsBySentenceCountSorter sorter = new ParagraphsBySentenceCountSorter();
            sorter.sortParagraphsBySentenceCount(textComponent);
            LOG.trace("---------After sorting paragraphs by sentence count");
            LOG.trace("\n{}", textComponent);

        } catch (TextParseException | SortException | SourceTextReaderException | FileNotFoundException e) {
            LOG.error(e);
        }
    }

    private static File getFileFromResources(String filename) throws FileNotFoundException {
        URL sourceTextUrl = Runner.class.getClassLoader().getResource(filename);
        if (sourceTextUrl == null) {
            throw new FileNotFoundException();
        }
        return new File(sourceTextUrl.getFile());
    }
}
