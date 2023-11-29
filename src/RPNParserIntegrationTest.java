import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests d'intégration pour RPNParserImpl.
 * Ces tests vérifient le comportement correct de RPNParserImpl en utilisant une instance réelle de CalcImpl
 * et StackImpl, couvrant diverses opérations arithmétiques et scénarios d'exception.
 */
public class RPNParserIntegrationTest {

    private RPNParser parser;

    /**
     * Configure l'environnement de test avant chaque test.
     */
    @BeforeEach
    public void setup() {
        StackImpl stack = new StackImpl();
        CalcImpl calc = new CalcImpl(stack);
        parser = new RPNParserImpl(calc);
    }

    /**
     * Teste une addition simple en notation RPN.
     */
    @Test
    public void testBasicAddition() throws Exception {
        assertEquals(8.0, parser.parseAndDisplayResult("3 5 +"));
    }

    /**
     * Teste une soustraction en notation RPN.
     */
    @Test
    public void testSubtraction() throws Exception {
        assertEquals(-10.0, parser.parseAndDisplayResult("10 20 -"));
    }

    /**
     * Teste une division en notation RPN.
     */
    @Test
    public void testDivision() throws Exception {
        assertEquals(0.5, parser.parseAndDisplayResult("10 20 /"));
    }

    /**
     * Teste une addition multiple en notation RPN.
     */
    @Test
    public void testMultipleAddition() throws Exception {
        assertEquals(4.0, parser.parseAndDisplayResult("1 1 1 1 + + +"));
    }

    /**
     * Teste l'interprétation d'une seule valeur en notation RPN.
     */
    @Test
    public void testSingleValue() throws Exception {
        assertEquals(5.0, parser.parseAndDisplayResult("5"));
    }

    /**
     * Teste le comportement face à un token invalide en notation RPN.
     */
    @Test
    public void testInvalidToken() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("5 a +"));
    }

    /**
     * Teste le comportement face à une division par zéro en notation RPN.
     */
    @Test
    public void testDivideByZero() {
        assertThrows(DivideByZeroException.class, () -> parser.parseAndDisplayResult("5 0 /"));
    }

    /**
     * Teste une exception lorsque pas assez d'opérandes sont fournies pour une opération en notation RPN.
     */
    @Test
    public void testNotEnoughOperands() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("5 +"));
    }

    /**
     * Teste le comportement lors de l'analyse d'une chaîne vide en notation RPN.
     */
    @Test
    public void testEmptyString() throws Exception {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult(""));
    }

    /**
     * Teste l'analyse d'une expression avec de multiples espaces en notation RPN.
     */
    @Test
    public void testMultipleSpaces() throws Exception {
        assertEquals(7.0, parser.parseAndDisplayResult("  3   4   +  "));
    }

    /**
     * Teste l'analyse d'une expression complexe en notation RPN.
     */
    @Test
    public void testComplexExpression() throws Exception {
        assertEquals(-1.3, parser.parseAndDisplayResult("3 5 2 * + 10 20 - /"));
    }

    /**
     * Teste le traitement d'un nombre négatif unique en notation RPN.
     */
    @Test
    public void testUnaryNegative() throws Exception {
        assertEquals(-5.0, parser.parseAndDisplayResult("-5"));
    }

    /**
     * Teste l'analyse d'expressions avec des nombres décimaux en notation RPN.
     */
    @Test
    public void testDecimalNumbers() throws Exception {
        assertEquals(5.5, parser.parseAndDisplayResult("2.5 3 +"));
    }

    /**
     * Teste l'analyse d'expressions avec de très grands nombres en notation RPN.
     */
    @Test
    public void testLargeNumbers() throws Exception {
        assertEquals(1.0E8 + 1.0E8, parser.parseAndDisplayResult("1.0E8 1.0E8 +"));
    }

    /**
     * Teste l'analyse d'expressions avec de très petits nombres en notation RPN.
     */
    @Test
    public void testTinyNumbers() throws Exception {
        assertEquals(1.0E-8 + 1.0E-8, parser.parseAndDisplayResult("1.0E-8 1.0E-8 +"));
    }

    /**
     * Teste une exception lorsque des opérateurs sont utilisés sans un nombre suffisant d'opérandes en notation RPN.
     */
    @Test
    public void testMismatchedOperators() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("+ + +"));
    }

    /**
     * Teste une exception lorsque seulement des opérateurs sont fournis sans opérandes en notation RPN.
     */
    @Test
    public void testOnlyOperators() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("+ - * /"));
    }

    /**
     * Teste l'analyse d'une expression avec des espacements trompeurs en notation RPN.
     */
    @Test
    public void testTrickySpaces() throws Exception {
        assertEquals(9.0, parser.parseAndDisplayResult("   4    5 +    "));
    }

    /**
     * Teste l'analyse d'une séquence d'opérations multiples en notation RPN.
     */
    @Test
    public void testMultipleOperationsTogether() throws Exception {
        assertEquals(27.0, parser.parseAndDisplayResult("10 5 * 2 / 2 +"));
    }

    /**
     * Teste une exception lorsque l'expression commence par un opérateur en notation RPN.
     */
    @Test
    public void testStartingWithOperation() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("+ 5 5"));
    }

    /**
     * Teste une exception lorsque l'expression se termine par un opérateur en notation RPN.
     */
    @Test
    public void testEndingWithOperation() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> parser.parseAndDisplayResult("5 5 + *"));
    }

    /**
     * Teste une exception pour un nombre avec plusieurs points décimaux en notation RPN.
     */
    @Test
    public void testMultipleDecimalPoints() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("5.5.5 3 +"));
    }

    /**
     * Teste une exception pour un nombre avec plusieurs signes négatifs en notation RPN.
     */
    @Test
    public void testMultipleNegativeSigns() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("--5 3 +"));
    }

    /**
     * Teste une exception pour l'utilisation de caractères invalides en notation RPN.
     */
    @Test
    public void testInvalidCharacters() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("5 & +"));
    }

    /**
     * Teste une exception pour un nombre suivi directement d'un opérateur sans espace en notation RPN.
     */
    @Test
    public void testNumberThenOperatorWithoutSpace() {
        assertThrows(ParseException.class, () -> parser.parseAndDisplayResult("5+3"));
    }

    /**
     * Teste le comportement de l'arrondi des nombres à virgule flottante en notation RPN.
     */
    @Test
    public void testFloatingPointRounding() throws Exception {
        assertEquals(0.3, parser.parseAndDisplayResult("0.1 0.2 +"), 0.000001);  // tolérance spécifiée
    }

    /**
     * Teste le comportement de l'arrondi lors de la division en notation RPN.
     */
    @Test
    public void testDivisionRounding() throws Exception {
        assertEquals(1.6666666666666667, parser.parseAndDisplayResult("5 3 /"), 0.0000000000000001);
    }

    /**
     * Teste l'analyse d'une expression résultant en un nombre négatif en notation RPN.
     */
    @Test
    public void testNegativeResults() throws Exception {
        assertEquals(-1.0, parser.parseAndDisplayResult("0 1 -"));
    }

    /**
     * Teste l'analyse d'une expression résultant en zéro en notation RPN.
     */
    @Test
    public void testResultIsZero() throws Exception {
        assertEquals(0.0, parser.parseAndDisplayResult("5 5 -"));
    }

}
