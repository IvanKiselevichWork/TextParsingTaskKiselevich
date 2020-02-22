package by.kiselevich.parsingtask.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static by.kiselevich.parsingtask.utils.OperatorConstant.*;

public class InfixToReversePolishNotationParser {

    private static final String SINGLE_SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final char SINGLE_DOT = '.';

    private static final Logger LOG = LogManager.getLogger(InfixToReversePolishNotationParser.class);

    public List<String> convertInfixToReversePolishNotation(String expression) {

        expression = expression.replace(SINGLE_SPACE, EMPTY_STRING);
        List<String> result = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        int index = 0;
        while (index < expression.length()) {
            if (Character.isDigit(expression.charAt(index))) {
                int numberStart = index;
                while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                    index++;
                }
                if (index < expression.length() && expression.charAt(index) == SINGLE_DOT) {
                    index++;
                    while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                        index++;
                    }
                }
                String number = expression.substring(numberStart, index);
                result.add(number);
            } else if (expression.charAt(index) == NOT_OPERATOR.charAt(0) || expression.charAt(index) == OPENING_BRACKET_OPERATOR.charAt(0)) {
                stack.addFirst(String.valueOf(expression.charAt(index)));
                index++;
            } else if (expression.charAt(index) == CLOSING_BRACKET_OPERATOR.charAt(0)) {
                String stackElement;
                try {
                    while (!(stackElement = stack.removeFirst()).equals(OPENING_BRACKET_OPERATOR)) {
                        result.add(stackElement);
                    }
                } catch (NoSuchElementException e) {
                    LOG.warn("Incorrect expression");
                    return new ArrayList<>();
                }
                index++;
            } else {
                String operation;
                if (expression.charAt(index) == LEFT_SHIFT_OPERATOR.charAt(0) || expression.charAt(index) == RIGHT_SHIFT_OPERATOR.charAt(0)) {
                    operation = expression.substring(index, index + 2);
                    index++;
                } else {
                    operation = expression.substring(index, index + 1);
                }

                String stackElement;
                while (!stack.isEmpty()) {
                    stackElement = stack.removeFirst();
                    if (stackElement.equals(NOT_OPERATOR) || getOperationPriority(stackElement) < getOperationPriority(operation)) {
                        result.add(stackElement);
                    } else {
                        stack.addFirst(stackElement);
                        break;
                    }
                }
                stack.addFirst(operation);
                index++;
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.removeFirst());
        }
        return result;
    }

    private int getOperationPriority(String operation) {
        switch (operation) {
            case NOT_OPERATOR:
                return 1;
            case MULTIPLICATION_OPERATOR:
            case DIVISION_OPERATOR:
                return 2;
            case ADDITION_OPERATOR:
            case SUBTRACTION_OPERATOR:
                return 3;
            case LEFT_SHIFT_OPERATOR:
            case RIGHT_SHIFT_OPERATOR:
                return 4;
            case AND_OPERATOR:
                return 5;
            case POWER_OPERATOR:
                return 6;
            case OR_OPERATOR:
                return 7;
            case OPENING_BRACKET_OPERATOR:
            case CLOSING_BRACKET_OPERATOR:
                return 9;
            default:
                return 10;
        }
    }
}
