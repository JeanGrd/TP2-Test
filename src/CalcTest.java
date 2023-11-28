import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcTest {

    private Calc calculator;

    @BeforeEach
    public void setUp() {
        calculator = new CalcImpl(new StackImpl());
    }

    @Test
    public void testEnterValue() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(10.0);
        assertEquals(10.0, calculator.displayValueOnTop());
    }

    @Test
    public void testAddition() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(5.0);
        calculator.enterValue(3.0);
        calculator.add();
        assertEquals(8.0, calculator.displayValueOnTop());
    }

    @Test
    public void testSubtraction() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(5.0);
        calculator.enterValue(3.0);
        calculator.subtract();
        assertEquals(2.0, calculator.displayValueOnTop());
    }

    @Test
    public void testMultiplication() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(5.0);
        calculator.enterValue(3.0);
        calculator.multiply();
        assertEquals(15.0, calculator.displayValueOnTop());
    }

    @Test
    public void testDivision() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        calculator.enterValue(6.0);
        calculator.enterValue(3.0);
        calculator.divide();
        assertEquals(2.0, calculator.displayValueOnTop());
    }

    @Test
    public void testDivideByZeroException() {
        calculator.enterValue(6.0);
        calculator.enterValue(0.0);
        assertThrows(DivideByZeroException.class, () -> calculator.divide());
    }

    @Test
    public void testNotEnoughOperandsException() {
        calculator.enterValue(5.0);
        assertThrows(NotEnoughOperandsOnStackException.class, () -> calculator.add());
    }

    @Test
    public void testSequentialOperations() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        calculator.enterValue(10.0);
        calculator.enterValue(5.0);
        calculator.subtract();
        calculator.enterValue(2.0);
        calculator.multiply();
        assertEquals(10.0, calculator.displayValueOnTop());
    }

    @Test
    public void testNegativeNumbers() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(-5.0);
        calculator.enterValue(3.0);
        calculator.add();
        assertEquals(-2.0, calculator.displayValueOnTop());
    }

    @Test
    public void testDecimalNumbers() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(10.5);
        calculator.enterValue(0.5);
        calculator.add();
        assertEquals(11.0, calculator.displayValueOnTop());
    }

    @Test
    public void testLargeNumbers() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.MAX_VALUE);
        calculator.enterValue(Double.MAX_VALUE);
        calculator.add();
        assertEquals(Double.POSITIVE_INFINITY, calculator.displayValueOnTop());
    }

    @Test
    public void testSmallNumbers() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.MIN_VALUE);
        calculator.enterValue(Double.MIN_VALUE);
        calculator.add();
        assertTrue(calculator.displayValueOnTop() > 0);
    }

    @Test
    public void testOverflow() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.MAX_VALUE);
        calculator.enterValue(Double.MAX_VALUE);
        calculator.multiply();
        assertEquals(Double.POSITIVE_INFINITY, calculator.displayValueOnTop());
    }

    @Test
    public void testUnderflow() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.MIN_VALUE);
        calculator.enterValue(Double.MIN_VALUE);
        calculator.multiply();
        assertEquals(0.0, calculator.displayValueOnTop());
    }

    @Test
    public void testMultipleDivideByZero() {
        calculator.enterValue(6.0);
        calculator.enterValue(0.0);
        assertThrows(DivideByZeroException.class, () -> calculator.divide());
    }

    @Test
    public void testChainedExceptions() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> calculator.add());
        calculator.enterValue(5.0);
        assertThrows(NotEnoughOperandsOnStackException.class, () -> calculator.add());
        calculator.enterValue(3.0);
        assertDoesNotThrow(() -> calculator.add());
    }

    @Test
    public void testDisplayWithoutValues() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> calculator.displayValueOnTop());
    }

    @Test
    public void testZeroOperations() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        calculator.enterValue(0.0);
        calculator.enterValue(0.0);
        calculator.add();
        assertEquals(0.0, calculator.displayValueOnTop());

        calculator.enterValue(5.0);
        calculator.multiply();
        assertEquals(0.0, calculator.displayValueOnTop());

        calculator.enterValue(5.0);
        calculator.divide();
        assertEquals(0.0, calculator.displayValueOnTop());
    }

    @Test
    public void testPushingNaN() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.NaN);
        assertEquals(Double.NaN, calculator.displayValueOnTop());

        calculator.enterValue(5.0);
        calculator.add();
        assertEquals(Double.NaN, calculator.displayValueOnTop()); // Addition avec NaN donne toujours NaN
    }

    @Test
    public void testPushingInfinity() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.POSITIVE_INFINITY);
        calculator.enterValue(5.0);
        calculator.add();
        assertEquals(Double.POSITIVE_INFINITY, calculator.displayValueOnTop()); // Infinity + quelque chose reste Infinity

        assertThrows(NotEnoughOperandsOnStackException.class, () -> calculator.subtract());
        assertEquals(Double.POSITIVE_INFINITY, calculator.displayValueOnTop()); // Infinity - quelque chose reste Infinity

        calculator.enterValue(Double.NEGATIVE_INFINITY);
        calculator.add();
        assertEquals(Double.NaN, calculator.displayValueOnTop()); // Infinity + (-Infinity) donne NaN
    }

    @Test
    public void testEdgeCasesOfDivision() {
        calculator.enterValue(Double.MIN_VALUE);
        calculator.enterValue(Double.MAX_VALUE);
        assertDoesNotThrow(() -> calculator.divide()); // Très petit nombre divisé par très grand nombre

        calculator.enterValue(0.0);
        calculator.enterValue(Double.MIN_VALUE);
        assertDoesNotThrow(() -> calculator.divide()); // Zero divisé par très petit nombre
    }

}
