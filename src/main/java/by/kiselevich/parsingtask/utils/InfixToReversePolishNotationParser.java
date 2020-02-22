package by.kiselevich.parsingtask.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class InfixToReversePolishNotationParser {

    private static final Logger LOG = LogManager.getLogger(InfixToReversePolishNotationParser.class);

    public List<String> convertInfixToReversePolishNotation(String expression) {

        expression = expression.replace(" ", "");
        List<String> result = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        int index = 0;
        while (index < expression.length()) {
            if (Character.isDigit(expression.charAt(index))) {
                int numberStart = index;
                while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                    index++;
                }
                if (index < expression.length() && expression.charAt(index) == '.') {
                    index++;
                    while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                        index++;
                    }
                }
                String number = expression.substring(numberStart, index);
                result.add(number);
            } else if (expression.charAt(index) == '~' || expression.charAt(index) == '(') {
                stack.addFirst(String.valueOf(expression.charAt(index)));
                index++;
            } else if (expression.charAt(index) == ')') {
                String stackElement;
                try {
                    while (!(stackElement = stack.removeFirst()).equals("(")) {
                        result.add(stackElement);
                    }
                } catch (NoSuchElementException e) {
                    LOG.warn("Incorrect expression");
                    return new LinkedList<>();
                }
                index++;
            } else {
                String operation;
                if (expression.charAt(index) == '<' || expression.charAt(index) == '>') {
                    operation = expression.substring(index, index + 2);
                    index++;
                } else {
                    operation = expression.substring(index, index + 1);
                }

                String stackElement;
                while (!stack.isEmpty()) {
                    stackElement = stack.removeFirst();
                    if (stackElement.equals("~") || getOperationPriority(stackElement) < getOperationPriority(operation)) {
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
            case "~":
                return 1;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 3;
            case "<<":
            case ">>":
                return 4;
            case "&":
                return 5;
            case "^":
                return 6;
            case "|":
                return 7;
            case "(":
            case ")":
                return 9;
            default:
                return 10;
        }
    }
}
