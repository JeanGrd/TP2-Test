import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

public class StackImplTest {

    private Stack stack;

    @BeforeEach
    public void setUp() {
        stack = new StackImpl();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(1.0);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

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

    @Test
    public void testPush() {
        stack.push(1.0);
        assertEquals(1, stack.getSize());
        assertEquals(1.0, stack.peek());
        stack.push(2.0);
        assertEquals(2, stack.getSize());
        assertEquals(2.0, stack.peek());
    }

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

    @Test
    public void testVeryLargeNumbers() {
        stack.push(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, stack.peek());
        stack.push(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, stack.peek());
    }

    @Test
    public void testNegativeNumbers() {
        stack.push(-1.0);
        assertEquals(-1.0, stack.peek());
    }

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

    @Test
    public void testNaN() {
        stack.push(Double.NaN);
        assertTrue(Double.isNaN(stack.peek()));
    }

    @Test
    public void testInfinity() {
        stack.push(Double.POSITIVE_INFINITY);
        assertEquals(Double.POSITIVE_INFINITY, stack.peek());
        stack.push(Double.NEGATIVE_INFINITY);
        assertEquals(Double.NEGATIVE_INFINITY, stack.peek());
    }

    @Test
    public void testPushAfterPop() {
        stack.push(1.0);
        stack.push(2.0);
        stack.pop();
        stack.push(3.0);
        assertEquals(3.0, stack.peek());
    }

    @Test
    public void testRepeatedPeek() throws EmptyStackException {
        stack.push(1.0);
        assertEquals(1.0, stack.peek());
        assertEquals(1.0, stack.peek());
        stack.push(2.0);
        assertEquals(2.0, stack.peek());
        assertEquals(2.0, stack.peek());
    }

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

    @Test
    public void resetStack() {
        stack.push(1.0);
        stack.push(2.0);
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testVeryCloseValues() {
        stack.push(1.0000001);
        stack.push(1.0000002);
        assertEquals(1.0000002, stack.peek());
        assertEquals(1.0000002, stack.pop());
        assertEquals(1.0000001, stack.peek());
    }

    @Test
    public void repeatedPushAndPop() {
        for (int i = 0; i < 100; i++) {
            stack.push(i);
            assertEquals(i, stack.peek());
            assertEquals(i, stack.pop());
            assertTrue(stack.isEmpty());
        }
    }

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

    @Test
    public void pushAfterMultiplePeek() {
        stack.push(1.0);
        assertEquals(1.0, stack.peek());
        assertEquals(1.0, stack.peek());
        stack.push(2.0);
        assertEquals(2.0, stack.peek());
    }

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