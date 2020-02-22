package by.kiselevich.parsingtask.utils;

import by.kiselevich.parsingtask.exception.WrongExpressionException;

import java.util.*;
import java.util.regex.Pattern;

public class ReversePolishNotationCalculator {

    public int calculateExpressionInReversePolishNotation(List<String> expression) throws WrongExpressionException {

        try {
            Deque<Integer> stack = new ArrayDeque<>();
            for (String operand : expression) {
                if (isNumber(operand)) {
                    stack.addFirst(Integer.parseInt(operand));
                } else {
                    int value1 = stack.removeFirst();
                    if (operand.equals("~")) {
                        stack.addFirst(makeOperation(operand, value1, 0));
                    } else {
                        int value2 = stack.removeFirst();
                        stack.addFirst(makeOperation(operand, value2, value1));
                    }
                }
            }

            return stack.removeFirst();
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
