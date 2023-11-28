import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class RPNParserTest {

    private RPNParser parser;

    @BeforeEach
    public void setup() {
        parser = new RPNNN();
    }

    @Test
    public void testBasicAddition() throws Exception {
        assertEquals(13.0, parser.parseAndDisplayResult("3 5 2 * +"));
    }

    @Test
    public void testSubtraction() throws Exception {
        assertEquals(-10.0, parser.parseAndDisplayResult("10 20 -"));
    }

    @Test
    public void testDivision() throws Exception {
        assertEquals(0.5, parser.parseAndDisplayResult("10 20 /"));
    }

    @Test
    public void testMultipleAddition() throws Exception {
        assertEquals(4.0, parser.parseAndDisplayResult("1 1 1 1 + + +"));
    }

    @Test
    public void testSingleValue() throws Exception {
        assertEquals(5.0, parser.parseAndDisplayResult("5"));
    }

    @Test
    public void testInvalidToken() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("5 a +"));
    }

    @Test
    public void testDivideByZero() {
        assertThrows(DivideByZeroException.class, () -> parser.parseAndDisplayResult("5 0 /"));
    }

    @Test
    public void testNotEnoughOperands() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("5 +"));
    }

    @Test
    public void testEmptyString() throws Exception {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult(""));
    }

    @Test
    public void testMultipleSpaces() throws Exception {
        assertEquals(7.0, parser.parseAndDisplayResult("  3   4   +  "));
    }

    @Test
    public void testComplexExpression() throws Exception {
        assertEquals(-1.3, parser.parseAndDisplayResult("3 5 2 * + 10 20 - /"));
    }

    @Test
    public void testUnaryNegative() throws Exception {
        assertEquals(-5.0, parser.parseAndDisplayResult("-5"));
    }

    @Test
    public void testDecimalNumbers() throws Exception {
        assertEquals(5.5, parser.parseAndDisplayResult("2.5 3 +"));
    }

    @Test
    public void testLargeNumbers() throws Exception {
        assertEquals(1.0E8 + 1.0E8, parser.parseAndDisplayResult("1.0E8 1.0E8 +"));
    }

    @Test
    public void testTinyNumbers() throws Exception {
        assertEquals(1.0E-8 + 1.0E-8, parser.parseAndDisplayResult("1.0E-8 1.0E-8 +"));
    }

    @Test
    public void testMismatchedOperators() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("+ + +"));
    }

    @Test
    public void testOnlyOperators() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("+ - * /"));
    }

    @Test
    public void testTrickySpaces() throws Exception {
        assertEquals(9.0, parser.parseAndDisplayResult("   4    5 +    "));
    }

    @Test
    public void testMultipleOperationsTogether() throws Exception {
        assertEquals(27.0, parser.parseAndDisplayResult("10 5 * 2 / 2 +"));
    }

    @Test
    public void testStartingWithOperation() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("+ 5 5"));
    }

    @Test
    public void testEndingWithOperation() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("5 5 + *"));
    }

    @Test
    public void testMultipleDecimalPoints() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("5.5.5 3 +"));
    }

    @Test
    public void testMultipleNegativeSigns() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("--5 3 +"));
    }

    @Test
    public void testInvalidCharacters() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("5 & +"));
    }

    @Test
    public void testNumberThenOperatorWithoutSpace() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("5+3"));
    }

    @Test
    public void testFloatingPointRounding() throws Exception {
        assertEquals(0.3, parser.parseAndDisplayResult("0.1 0.2 +"), 0.000001);  // tolérance spécifiée
    }

    @Test
    public void testDivisionRounding() throws Exception {
        assertEquals(1.6666666666666667, parser.parseAndDisplayResult("5 3 /"), 0.0000000000000001);
    }

    @Test
    public void testNegativeResults() throws Exception {
        assertEquals(-1.0, parser.parseAndDisplayResult("0 1 -"));
    }

    @Test
    public void testResultIsZero() throws Exception {
        assertEquals(0.0, parser.parseAndDisplayResult("5 5 -"));
    }

}
