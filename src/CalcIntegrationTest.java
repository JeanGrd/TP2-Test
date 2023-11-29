import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests d'intégration pour CalcImpl.
 * Ces tests vérifient le comportement de CalcImpl en utilisant une instance réelle de StackImpl,
 * couvrant diverses opérations arithmétiques et scénarios d'exception.
 */
public class CalcIntegrationTest {

    private Calc calculator;

    /**
     * Initialise le contexte de test avant chaque méthode de test.
     */
    @BeforeEach
    public void setUp() {
        calculator = new CalcImpl(new StackImpl());
    }

    /**
     * Teste l'entrée d'une valeur simple dans la calculatrice.
     */
    @Test
    public void testEnterValue() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(10.0);
        assertEquals(10.0, calculator.displayValueOnTop());
    }

    /**
     * Teste la fonctionnalité d'addition de la calculatrice.
     */
    @Test
    public void testAddition() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(5.0);
        calculator.enterValue(3.0);
        calculator.add();
        assertEquals(8.0, calculator.displayValueOnTop());
    }

    /**
     * Teste la fonctionnalité de soustraction de la calculatrice.
     */
    @Test
    public void testSubtraction() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(5.0);
        calculator.enterValue(3.0);
        calculator.subtract();
        assertEquals(2.0, calculator.displayValueOnTop());
    }

    /**
     * Teste la fonctionnalité de multiplication de la calculatrice.
     */
    @Test
    public void testMultiplication() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(5.0);
        calculator.enterValue(3.0);
        calculator.multiply();
        assertEquals(15.0, calculator.displayValueOnTop());
    }

    /**
     * Teste la fonctionnalité de division de la calculatrice.
     */
    @Test
    public void testDivision() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        calculator.enterValue(6.0);
        calculator.enterValue(3.0);
        calculator.divide();
        assertEquals(2.0, calculator.displayValueOnTop());
    }

    /**
     * Teste le déclenchement d'une exception lors d'une tentative de division par zéro.
     */
    @Test
    public void testDivideByZeroException() {
        calculator.enterValue(6.0);
        calculator.enterValue(0.0);
        assertThrows(DivideByZeroException.class, () -> calculator.divide());
    }

    /**
     * Teste le déclenchement d'une exception lorsque les opérations sont effectuées avec un nombre insuffisant d'opérandes.
     */
    @Test
    public void testNotEnoughOperandsException() {
        calculator.enterValue(5.0);
        assertThrows(NotEnoughOperandsOnStackException.class, () -> calculator.add());
    }

    /**
     * Teste une séquence d'opérations arithmétiques en chaîne pour vérifier le comportement cumulatif de la calculatrice.
     */
    @Test
    public void testSequentialOperations() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        calculator.enterValue(10.0);
        calculator.enterValue(5.0);
        calculator.subtract();
        calculator.enterValue(2.0);
        calculator.multiply();
        assertEquals(10.0, calculator.displayValueOnTop());
    }

    /**
     * Teste le traitement des nombres négatifs dans les opérations arithmétiques.
     */
    @Test
    public void testNegativeNumbers() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(-5.0);
        calculator.enterValue(3.0);
        calculator.add();
        assertEquals(-2.0, calculator.displayValueOnTop());
    }

    /**
     * Teste l'addition avec des nombres décimaux.
     */
    @Test
    public void testDecimalNumbers() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(10.5);
        calculator.enterValue(0.5);
        calculator.add();
        assertEquals(11.0, calculator.displayValueOnTop());
    }

    /**
     * Teste l'addition avec de très grands nombres pour vérifier la gestion des débordements.
     */
    @Test
    public void testLargeNumbers() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.MAX_VALUE);
        calculator.enterValue(Double.MAX_VALUE);
        calculator.add();
        assertEquals(Double.POSITIVE_INFINITY, calculator.displayValueOnTop());
    }

    /**
     * Teste l'addition avec de très petits nombres.
     */
    @Test
    public void testSmallNumbers() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.MIN_VALUE);
        calculator.enterValue(Double.MIN_VALUE);
        calculator.add();
        assertTrue(calculator.displayValueOnTop() > 0);
    }

    /**
     * Teste la multiplication avec de très grands nombres pour vérifier la gestion des débordements.
     */
    @Test
    public void testOverflow() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.MAX_VALUE);
        calculator.enterValue(Double.MAX_VALUE);
        calculator.multiply();
        assertEquals(Double.POSITIVE_INFINITY, calculator.displayValueOnTop());
    }

    /**
     * Teste la multiplication avec de très petits nombres pour vérifier la gestion des sous-débordements.
     */
    @Test
    public void testUnderflow() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.MIN_VALUE);
        calculator.enterValue(Double.MIN_VALUE);
        calculator.multiply();
        assertEquals(0.0, calculator.displayValueOnTop());
    }

    /**
     * Teste une série d'opérations de division par zéro pour vérifier la gestion des exceptions.
     */
    @Test
    public void testMultipleDivideByZero() {
        calculator.enterValue(6.0);
        calculator.enterValue(0.0);
        assertThrows(DivideByZeroException.class, () -> calculator.divide());
    }

    /**
     * Teste le chaînage d'exceptions pour vérifier le comportement de la calculatrice dans des situations complexes.
     */
    @Test
    public void testChainedExceptions() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> calculator.add());
        calculator.enterValue(5.0);
        assertThrows(NotEnoughOperandsOnStackException.class, () -> calculator.add());
        calculator.enterValue(3.0);
        assertDoesNotThrow(() -> calculator.add());
    }

    /**
     * Teste l'affichage d'une valeur sans entrée préalable pour vérifier la gestion des piles vides.
     */
    @Test
    public void testDisplayWithoutValues() {
        assertThrows(NotEnoughOperandsOnStackException.class, () -> calculator.displayValueOnTop());
    }

    /**
     * Teste le comportement de la calculatrice avec des opérations impliquant zéro.
     */
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

    /**
     * Teste le comportement de la calculatrice lors de l'entrée de NaN (Not a Number).
     */
    @Test
    public void testPushingNaN() throws NotEnoughOperandsOnStackException {
        calculator.enterValue(Double.NaN);
        assertEquals(Double.NaN, calculator.displayValueOnTop());

        calculator.enterValue(5.0);
        calculator.add();
        assertEquals(Double.NaN, calculator.displayValueOnTop()); // Addition avec NaN donne toujours NaN
    }

    /**
     * Teste l'entrée et les opérations avec des valeurs infinies.
     */
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

    /**
     * Teste des cas limites de division.
     */
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
