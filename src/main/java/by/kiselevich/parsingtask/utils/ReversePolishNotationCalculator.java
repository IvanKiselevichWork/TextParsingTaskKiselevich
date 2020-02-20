package by.kiselevich.parsingtask.utils;

import by.kiselevich.parsingtask.exception.WrongExpressionException;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Pattern;

public class ReversePolishNotationCalculator {

    public int calculateExpressionInReversePolishNotation(LinkedList<String> expression) throws WrongExpressionException {

        try {
            Stack<Integer> stack = new Stack<>();
            for (String operand : expression) {
                if (isNumber(operand)) {
                    stack.push(Integer.parseInt(operand));
                } else {
                    int value1 = stack.pop();
                    if (operand.equals("~")) {
                        stack.push(makeOperation(operand, value1, 0));
                    } else {
                        int value2 = stack.pop();
                        stack.push(makeOperation(operand, value2, value1));
                    }
                }
            }

            return stack.pop();
        } catch (EmptyStackException e) {
            throw new WrongExpressionException("Wrong expression", e);
        }
    }

    private int makeOperation(String operation, int value1, int value2) throws WrongExpressionException {
        switch (operation) {
            case "~":
                return ~value1;
            case "*":
                return value1 * value2;
            case "/":
                return value1 / value2;
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "<<":
                return value1 << value2;
            case ">>":
                return value1 >> value2;
            case "&":
                return value1 & value2;
            case "^":
                return value1 ^ value2;
            case "|":
                return value1 | value2;
            default:
                throw new WrongExpressionException("Wrong operation: " + operation);
        }
    }

    private boolean isNumber(String operand) {
        String regex = "\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(operand).matches();
    }
}
