package by.kiselevich.parsingtask.parser;

import by.kiselevich.parsingtask.entity.TextComponent;
import by.kiselevich.parsingtask.exception.TextParseException;
import by.kiselevich.parsingtask.exception.WrongExpressionException;
import by.kiselevich.parsingtask.reversepolishnotationtools.InfixToReversePolishNotationParser;
import by.kiselevich.parsingtask.reversepolishnotationtools.ReversePolishNotationCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionToValueParser extends AbstractParser {

    private static final Logger LOG = LogManager.getLogger(ExpressionToValueParser.class);
    private static final String EXPRESSION_REGEX = "(?m)(~|~\\(*|\\(*~|\\()?\\d+(\\.\\d+)?(( *\\)* *)([-+*\\/&\\|^]|<<|>>) *~* *\\(* *~* *\\d+(\\.\\d+)?\\)*)+";
    private static final String WRONG_EXPRESSION = "WRONG EXPRESSION";

    @Override
    public TextComponent parse(String source) throws TextParseException {
        Pattern pattern = Pattern.compile(EXPRESSION_REGEX);
        Matcher matcher = pattern.matcher(source);
        StringBuffer stringBuffer = new StringBuffer();
        String expressionResult;
        while (matcher.find()) {
            try {
                expressionResult = String.valueOf(calculateExpression(matcher.group()));
            } catch (WrongExpressionException e) {
                LOG.warn("Wrong expression: {}", matcher.group());
                expressionResult = WRONG_EXPRESSION;
            }
            matcher.appendReplacement(stringBuffer, expressionResult);
        }
        matcher.appendTail(stringBuffer);
        return nextParser.parse(stringBuffer.toString());
    }

    private int calculateExpression(String expression) throws WrongExpressionException {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        InfixToReversePolishNotationParser notationConverter = new InfixToReversePolishNotationParser();

        return calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
    }

}
