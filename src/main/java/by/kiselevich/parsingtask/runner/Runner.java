package by.kiselevich.parsingtask.runner;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.exception.TextParseException;
import by.kiselevich.parsingtask.parser.*;
import by.kiselevich.parsingtask.replacer.ExpressionToValueReplacer;

public class Runner {
    public static void main(String[] args) {
        String sourceText = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
                "\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "\tBye.\n";

        AbstractParser textParser = new TextToParagraphsParser();
        AbstractParser paragraphParser = new ParagraphToSentencesParser();
        AbstractParser sentenceParser = new SentenceToLexemesParser();
        AbstractParser lexemeParser = new LexemeToWordsParser();
        AbstractParser wordParser = new WordToSymbolsParser();
        AbstractParser symbolParser = new SymbolToTextLeafParser();


        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        try {
            TextComponent textComponent = textParser.parse(sourceText);

            String out = textComponent.toString();
            System.out.println(out);

            System.out.println("\n\n ---------");
            System.out.println(new ExpressionToValueReplacer().replaceExpressionsToValues(sourceText));


        } catch (TextParseException e) {
            e.printStackTrace();
        }
    }
}
