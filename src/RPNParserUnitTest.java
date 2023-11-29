import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class RPNParserUnitTest {
    private RPNParserImpl rpn;
    private Calc calcFake;

    @BeforeEach
    void setUp() {
        calcFake = new CalcFakeImpl();
        rpn = new RPNParserImpl(calcFake);
    }

    @Test
    void testAdditionCalculation() throws Exception {
        double result = rpn.parseAndDisplayResult("2 4 +");
        assertEquals(6, result);
    }

    @Test
    void testSubstractionCalculation() throws Exception {
        double result = rpn.parseAndDisplayResult("2 4 -");
        assertEquals(2, result);
    }

    @Test
    void testMultiplicationCalculation() throws Exception {
        double result = rpn.parseAndDisplayResult("2 4 *");
        assertEquals(8, result);
    }

    @Test
    void testDivisionCalculation() throws Exception {
        double result = rpn.parseAndDisplayResult("2 4 /");
        assertEquals(2, result);
    }

    @Test
    void testParseException() {
        assertThrows(ParseException.class, () -> rpn.parseAndDisplayResult("3 a +"));
    }

    @Test
    void testDivideByZeroException() {
        assertThrows(DivideByZeroException.class, () -> rpn.parseAndDisplayResult("3 0 /"));
    }
}
