package by.kiselevich.parsingtask.replacer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionToValueReplacer {

    private static final String EXPRESSION_REGEX = "(?m)(~|~\\(*|\\(*~|\\()?\\d+(\\.\\d+)?(( *\\)* *)([-+*\\/&\\|^]|<<|>>) *~* *\\(* *~* *\\d+(\\.\\d+)?\\)*)+";
    private InfixToReversePolishNotationParser notationConverter = new InfixToReversePolishNotationParser();


    public String replaceExpressionsToValues(String source) {

        Pattern pattern = Pattern.compile(EXPRESSION_REGEX);
        Matcher matcher = pattern.matcher(source);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, calculateExpression(matcher.group()));
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private String calculateExpression(String expression) {
        System.out.print(expression + " -> ");
        String result = "";
        for(String string : notationConverter.convertInfixToReversePolishNotation(expression)) {
            result += string + " ";
        }
        System.out.println(result);
        return "NULL";
    }
}
