package by.kiselevich.parsingtask.utils;

import by.kiselevich.parsingtask.exception.WrongExpressionException;

import java.util.*;
import java.util.regex.Pattern;

import static by.kiselevich.parsingtask.utils.OperatorConstant.*;

public class ReversePolishNotationCalculator {

    private static final String WRONG_OPERATOR_EXCEPTION_MESSAGE = "Wrong operation: ";
    private static final String WRONG_EXPRESSION_EXCEPTION_MESSAGE = "Wrong operation: ";

    private static final String IS_NUMBER_REGEX = "\\d+(\\.\\d+)?";

    public int calculateExpressionInReversePolishNotation(List<String> expression) throws WrongExpressionException {

        try {
            Deque<Integer> stack = new ArrayDeque<>();
            for (String operand : expression) {
                if (isNumber(operand)) {
                    stack.addFirst(Integer.parseInt(operand));
                } else {
                    int value1 = stack.removeFirst();
                    if (operand.equals(NOT_OPERATOR)) {
                        stack.addFirst(makeOperation(operand, value1, 0));
                    } else {
                        int value2 = stack.removeFirst();
                        stack.addFirst(makeOperation(operand, value2, value1));
                    }
                }
            }
            return stack.removeFirst();
        } catch (EmptyStackException e) {
            throw new WrongExpressionException(WRONG_EXPRESSION_EXCEPTION_MESSAGE, e);
        }
    }

    private int makeOperation(String operation, int value1, int value2) throws WrongExpressionException {
        switch (operation) {
            case NOT_OPERATOR:
                return ~value1;
            case MULTIPLICATION_OPERATOR:
                return value1 * value2;
            case DIVISION_OPERATOR:
                return value1 / value2;
            case ADDITION_OPERATOR:
                return value1 + value2;
            case SUBTRACTION_OPERATOR:
                return value1 - value2;
            case LEFT_SHIFT_OPERATOR:
                return value1 << value2;
            case RIGHT_SHIFT_OPERATOR:
                return value1 >> value2;
            case AND_OPERATOR:
                return value1 & value2;
            case POWER_OPERATOR:
                return value1 ^ value2;
            case OR_OPERATOR:
                return value1 | value2;
            default:
                throw new WrongExpressionException(WRONG_OPERATOR_EXCEPTION_MESSAGE + operation);
        }
    }

    private boolean isNumber(String operand) {
        Pattern pattern = Pattern.compile(IS_NUMBER_REGEX);
        return pattern.matcher(operand).matches();
    }
}
