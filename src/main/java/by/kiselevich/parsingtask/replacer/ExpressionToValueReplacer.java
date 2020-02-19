package by.kiselevich.parsingtask.replacer;

import by.kiselevich.parsingtask.exception.WrongExpressionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionToValueReplacer {

    private static final Logger LOG = LogManager.getLogger(ExpressionToValueReplacer.class);
    private static final String EXPRESSION_REGEX = "(?m)(~|~\\(*|\\(*~|\\()?\\d+(\\.\\d+)?(( *\\)* *)([-+*\\/&\\|^]|<<|>>) *~* *\\(* *~* *\\d+(\\.\\d+)?\\)*)+";
    private static final String WRONG_EXPRESSION = "WRONG EXPRESSION";
    private InfixToReversePolishNotationParser notationConverter = new InfixToReversePolishNotationParser();

    public String replaceExpressionsToValues(String source) {

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
        return stringBuffer.toString();
    }

    private int calculateExpression(String expression) throws WrongExpressionException {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        return calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
    }
}
