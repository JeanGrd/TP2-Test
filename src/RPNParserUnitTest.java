import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour RPNParserImpl.
 * Ces tests utilisent un CalcFakeImpl pour simuler les opérations de calcul
 * et se concentrent sur la vérification de l'analyse correcte des expressions RPN.
 */
public class RPNParserUnitTest {
    private RPNParserImpl rpn;
    private Calc calcFake;

    /**
     * Initialise les composants nécessaires avant chaque test.
     */
    @BeforeEach
    void setUp() {
        calcFake = new CalcFakeImpl();
        rpn = new RPNParserImpl(calcFake);
    }

    /**
     * Teste le calcul d'une addition en notation RPN.
     */
    @Test
    void testAdditionCalculation() throws Exception {
        double result = rpn.parseAndDisplayResult("2 4 +");
        assertEquals(6, result);
    }

    /**
     * Teste le calcul d'une soustraction en notation RPN.
     */
    @Test
    void testSubstractionCalculation() throws Exception {
        double result = rpn.parseAndDisplayResult("2 4 -");
        assertEquals(2, result);
    }

    /**
     * Teste le calcul d'une multiplication en notation RPN.
     */
    @Test
    void testMultiplicationCalculation() throws Exception {
        double result = rpn.parseAndDisplayResult("2 4 *");
        assertEquals(8, result);
    }

    /**
     * Teste le calcul d'une division en notation RPN.
     */
    @Test
    void testDivisionCalculation() throws Exception {
        double result = rpn.parseAndDisplayResult("2 4 /");
        assertEquals(2, result);
    }

    /**
     * Teste la génération d'une ParseException pour une expression RPN invalide.
     */
    @Test
    void testParseException() {
        assertThrows(ParseException.class, () -> rpn.parseAndDisplayResult("3 a +"));
    }

    /**
     * Teste la génération d'une DivideByZeroException pour une division par zéro en notation RPN.
     */
    @Test
    void testDivideByZeroException() {
        assertThrows(DivideByZeroException.class, () -> rpn.parseAndDisplayResult("3 0 /"));
    }
}
