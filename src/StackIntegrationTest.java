import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

/**
 * Classe de tests d'intégration pour StackImpl.
 * Ces tests vérifient le comportement correct de StackImpl dans divers scénarios,
 * y compris l'ajout, le retrait et la consultation d'éléments, ainsi que la gestion des cas spéciaux.
 */
public class StackIntegrationTest {

    private Stack stack;

    /**
     * Configure l'environnement de test avant chaque test.
     */
    @BeforeEach
    public void setUp() {
        stack = new StackImpl();
    }

    /**
     * Teste si la pile est correctement identifiée comme vide ou non.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(1.0);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    /**
     * Teste la méthode getSize pour vérifier si la taille de la pile est correctement rapportée.
     */
    @Test
    public void testGetSize() {
        assertEquals(0, stack.getSize());
        stack.push(1.0);
        assertEquals(1, stack.getSize());
        stack.push(2.0);
        assertEquals(2, stack.getSize());
        stack.pop();
        assertEquals(1, stack.getSize());
    }

    /**
     * Teste la fonctionnalité de la méthode push pour ajouter des éléments à la pile.
     */
    @Test
    public void testPush() {
        stack.push(1.0);
        assertEquals(1, stack.getSize());
        assertEquals(1.0, stack.peek());
        stack.push(2.0);
        assertEquals(2, stack.getSize());
        assertEquals(2.0, stack.peek());
    }

    /**
     * Teste la méthode peek pour s'assurer qu'elle retourne le bon élément sans le retirer de la pile.
     */
    @Test
    public void testPeek() throws EmptyStackException {
        assertThrows(EmptyStackException.class, () -> stack.peek());

        stack.push(1.0);
        assertEquals(1.0, stack.peek());
        assertEquals(1, stack.getSize());

        stack.push(2.0);
        assertEquals(2.0, stack.peek());
        assertEquals(2, stack.getSize());
    }

    /**
     * Teste la méthode pop pour vérifier si elle retire et retourne correctement le dernier élément.
     */
    @Test
    public void testPop() throws EmptyStackException {
        assertThrows(EmptyStackException.class, () -> stack.pop());

        stack.push(1.0);
        assertEquals(1.0, stack.pop());
        assertEquals(0, stack.getSize());

        stack.push(2.0);
        stack.push(3.0);
        assertEquals(3.0, stack.pop());
        assertEquals(1, stack.getSize());
        assertEquals(2.0, stack.pop());
        assertEquals(0, stack.getSize());
    }

    /**
     * Teste le comportement de la pile avec des valeurs très grandes.
     */
    @Test
    public void testVeryLargeNumbers() {
        stack.push(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, stack.peek());
        stack.push(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, stack.peek());
    }

    /**
     * Teste le comportement de la pile avec des nombres négatifs.
     */
    @Test
    public void testNegativeNumbers() {
        stack.push(-1.0);
        assertEquals(-1.0, stack.peek());
    }

    /**
     * Réalise un test de stress en ajoutant et en retirant un grand nombre d'éléments.
     */
    @Test
    public void stressTest() {
        int numberOfItems = 1000000;  // Un million
        for (int i = 0; i < numberOfItems; i++) {
            stack.push(i);
        }
        assertEquals(numberOfItems, stack.getSize());
        for (int i = numberOfItems - 1; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());
    }

    /**
     * Teste le comportement de la pile lorsqu'un élément NaN est poussé.
     */
    @Test
    public void testNaN() {
        stack.push(Double.NaN);
        assertTrue(Double.isNaN(stack.peek()));
    }

    /**
     * Teste le comportement de la pile lors de l'ajout d'éléments ayant des valeurs infinies.
     */
    @Test
    public void testInfinity() {
        stack.push(Double.POSITIVE_INFINITY);
        assertEquals(Double.POSITIVE_INFINITY, stack.peek());
        stack.push(Double.NEGATIVE_INFINITY);
        assertEquals(Double.NEGATIVE_INFINITY, stack.peek());
    }

    /**
     * Teste le comportement de la pile lors de l'ajout d'un élément après une opération de retrait.
     */
    @Test
    public void testPushAfterPop() {
        stack.push(1.0);
        stack.push(2.0);
        stack.pop();
        stack.push(3.0);
        assertEquals(3.0, stack.peek());
    }

    /**
     * Teste le fait de répéter la méthode peek sans modifier l'état de la pile.
     */
    @Test
    public void testRepeatedPeek() throws EmptyStackException {
        stack.push(1.0);
        assertEquals(1.0, stack.peek());
        assertEquals(1.0, stack.peek());
        stack.push(2.0);
        assertEquals(2.0, stack.peek());
        assertEquals(2.0, stack.peek());
    }

    /**
     * Teste le retrait de tous les éléments jusqu'à ce que la pile soit vide.
     */
    @Test
    public void testPopUntilEmpty() {
        stack.push(1.0);
        stack.push(2.0);
        stack.push(3.0);
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    /**
     * Teste une séquence alternée d'ajout, de consultation et de retrait d'éléments de la pile.
     */
    @Test
    public void alternatePushPeekPop() {
        stack.push(1.0);
        assertEquals(1.0, stack.peek());
        stack.push(2.0);
        assertEquals(2.0, stack.peek());
        assertEquals(2.0, stack.pop());
        assertEquals(1.0, stack.peek());
        assertEquals(1.0, stack.pop());
        assertTrue(stack.isEmpty());
    }

    /**
     * Teste la réinitialisation de la pile après plusieurs opérations.
     */
    @Test
    public void resetStack() {
        stack.push(1.0);
        stack.push(2.0);
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    /**
     * Teste le comportement de la pile avec des valeurs très proches l'une de l'autre.
     */
    @Test
    public void testVeryCloseValues() {
        stack.push(1.0000001);
        stack.push(1.0000002);
        assertEquals(1.0000002, stack.peek());
        assertEquals(1.0000002, stack.pop());
        assertEquals(1.0000001, stack.peek());
    }

    /**
     * Teste une série d'ajouts et de retraits répétés sur la pile.
     */
    @Test
    public void repeatedPushAndPop() {
        for (int i = 0; i < 100; i++) {
            stack.push(i);
            assertEquals(i, stack.peek());
            assertEquals(i, stack.pop());
            assertTrue(stack.isEmpty());
        }
    }

    /**
     * Teste une séquence complexe d'opérations sur la pile.
     */
    @Test
    public void complexSequenceOfOperations() {
        assertTrue(stack.isEmpty());
        stack.push(1.0);
        stack.push(2.0);
        assertEquals(2.0, stack.pop());
        stack.push(3.0);
        stack.push(4.0);
        assertEquals(4.0, stack.peek());
        assertEquals(4.0, stack.pop());
        assertEquals(3.0, stack.peek());
        assertEquals(3.0, stack.pop());
        assertEquals(1.0, stack.pop());
        assertTrue(stack.isEmpty());
    }

    /**
     * Teste le comportement de la pile après plusieurs opérations peek suivies d'un ajout.
     */
    @Test
    public void pushAfterMultiplePeek() {
        stack.push(1.0);
        assertEquals(1.0, stack.peek());
        assertEquals(1.0, stack.peek());
        stack.push(2.0);
        assertEquals(2.0, stack.peek());
    }

    /**
     * Teste le comportement de la pile avec la gestion de zéro, y compris le zéro négatif.
     */
    @Test
    public void testZeroHandling() {
        stack.push(0.0);
        assertEquals(0.0, stack.peek());
        stack.push(-0.0);  // Negative zero
        assertEquals(-0.0, stack.peek());
        assertEquals(-0.0, stack.pop());
        assertEquals(0.0, stack.pop());
    }

}