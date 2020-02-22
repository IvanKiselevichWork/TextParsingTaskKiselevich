package by.kiselevich.parsingtask.utils;

import by.kiselevich.parsingtask.exception.WrongExpressionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReversePolishNotationCalculatorTest extends Assert {

    private ReversePolishNotationCalculator calculator;
    private InfixToReversePolishNotationParser notationConverter;

    @Before
    public void init() {
        calculator = new ReversePolishNotationCalculator();
        notationConverter = new InfixToReversePolishNotationParser();
    }

    @Test
    public void testReversePolishNotationCalculator1() throws WrongExpressionException {
        String expression = "13<<2";
        int result = calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
        int correctResult = 13<<2;
        assertEquals(correctResult, result);
    }

    @Test
    public void testReversePolishNotationCalculator2() throws WrongExpressionException {
        String expression = "~6&9|(3&4)";
        int result = calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
        int correctResult = ~6&9|(3&4);
        assertEquals(correctResult, result);
    }

    @Test
    public void testReversePolishNotationCalculator3() throws WrongExpressionException {
        String expression = "5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1)";
        int result = calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
        int correctResult = 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1);
        assertEquals(correctResult, result);
    }

    @Test
    public void testReversePolishNotationCalculator4() throws WrongExpressionException {
        String expression = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
        int result = calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
        int correctResult = (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78;
        assertEquals(correctResult, result);
    }

    @Test
    public void testReversePolishNotationCalculator5() throws WrongExpressionException {
        String expression = "(4^5|1&2<<(2|5>>2&71))|1200";
        int result = calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
        int correctResult = (4^5|1&2<<(2|5>>2&71))|1200;
        assertEquals(correctResult, result);
    }

    @Test(expected = WrongExpressionException.class)
    public void testReversePolishNotationCalculatorFail1() throws WrongExpressionException {
        String expression = "(4^5|1&2<<(2|5>)>2&71))|1200";
        calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
    }

    @Test(expected = WrongExpressionException.class)
    public void testReversePolishNotationCalculatorFail2() throws WrongExpressionException {
        String expression = "(4^5^|1&2<<(2|5>>2&71))|1200";
        calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
    }

    @Test(expected = WrongExpressionException.class)
    public void testReversePolishNotationCalculatorFail3() throws WrongExpressionException {
        String expression = "((4^5|1&2<<(2|5>>2&71))|1200";
        calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
    }

    @Test(expected = WrongExpressionException.class)
    public void testReversePolishNotationCalculatorFail4() throws WrongExpressionException {
        String expression = null;
        calculator.calculateExpressionInReversePolishNotation(notationConverter.convertInfixToReversePolishNotation(expression));
    }
}
