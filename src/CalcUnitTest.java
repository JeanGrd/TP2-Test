import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de tests unitaires pour CalcImpl utilisant un StackFakeImpl.
 * Ces tests vérifient le comportement de CalcImpl dans un environnement contrôlé
 * où les interactions avec le Stack sont simulées par StackFakeImpl.
 */
public class CalcUnitTest {

    private CalcImpl calc;
    private StackFakeImpl fakeStack;

    /**
     * Prépare l'environnement de test avant chaque méthode de test.
     */
    @Before
    public void setUp() {
        fakeStack = new StackFakeImpl(false, 2, 8);
        calc = new CalcImpl(fakeStack);
    }

    /**
     * Teste la fonctionnalité d'addition de la calculatrice.
     */
    @Test
    public void testAdd() throws NotEnoughOperandsOnStackException {
        calc.add();
        assertEquals("L'addition de 8 et 8 doit être 16", calc.displayValueOnTop(), 16, 0.01);
    }

    /**
     * Teste la fonctionnalité de soustraction de la calculatrice.
     */
    @Test
    public void testSubtract() throws NotEnoughOperandsOnStackException {
        calc.subtract();
        assertEquals("La soustraction de 8 de 8 doit être 0", 0, calc.displayValueOnTop(), 0.01);
    }

    /**
     * Teste la fonctionnalité de multiplication de la calculatrice.
     */
    @Test
    public void testMultiply() throws NotEnoughOperandsOnStackException {
        calc.multiply();
        assertEquals("La multiplication de 8 et 8 doit être 64", 64, calc.displayValueOnTop(), 0.01);
    }

    /**
     * Teste la fonctionnalité de division de la calculatrice.
     */
    @Test
    public void testDivide() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        calc.divide();
        assertEquals("La division de 8 par 8 doit être 1", 1, calc.displayValueOnTop(), 0.01);
    }

    /**
     * Teste l'exception NotEnoughOperandsOnStackException lors d'une addition avec un nombre insuffisant d'opérandes.
     */
    @Test(expected = NotEnoughOperandsOnStackException.class)
    public void testAddWithNotEnoughOperands() throws NotEnoughOperandsOnStackException {
        this.fakeStack.size = 1;
        calc.add();
    }

    /**
     * Teste l'exception DivideByZeroException lors d'une division par zéro.
     */
    @Test(expected = DivideByZeroException.class)
    public void testDivideByZero() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        this.fakeStack.topValue = 0;
        calc.divide();
    }

    /**
     * Teste l'exception NotEnoughOperandsOnStackException lors de l'affichage de la valeur au sommet d'une pile vide.
     */
    @Test(expected = NotEnoughOperandsOnStackException.class)
    public void testDisplayValueOnTopWithEmptyStack() throws NotEnoughOperandsOnStackException {
        this.fakeStack.isEmpty = true;
        calc.displayValueOnTop();
    }

}

