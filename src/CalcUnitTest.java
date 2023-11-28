import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalcUnitTest {

    private CalcImpl calc;
    private StackFakeImpl fakeStack;

    @Before
    public void setUp() {
        fakeStack = new StackFakeImpl(false, 2, 8);
        calc = new CalcImpl(fakeStack);
    }

    @Test
    public void testAdd() throws NotEnoughOperandsOnStackException {
        calc.add();
        assertEquals("L'addition de 8 et 8 doit être 16", calc.displayValueOnTop(), 16, 0.01);
    }

    @Test
    public void testSubtract() throws NotEnoughOperandsOnStackException {
        calc.subtract();
        assertEquals("La soustraction de 8 de 8 doit être 0", 0, calc.displayValueOnTop(), 0.01);
    }

    @Test
    public void testMultiply() throws NotEnoughOperandsOnStackException {
        calc.multiply();
        assertEquals("La multiplication de 8 et 8 doit être 64", 64, calc.displayValueOnTop(), 0.01);
    }

    @Test
    public void testDivide() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        calc.divide();
        assertEquals("La division de 8 par 8 doit être 1", 1, calc.displayValueOnTop(), 0.01);
    }

    @Test(expected = NotEnoughOperandsOnStackException.class)
    public void testAddWithNotEnoughOperands() throws NotEnoughOperandsOnStackException {
        this.fakeStack.size = 1;
        calc.add();
    }

    @Test(expected = DivideByZeroException.class)
    public void testDivideByZero() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        this.fakeStack.topValue = 0;
        calc.divide();
    }

    @Test(expected = NotEnoughOperandsOnStackException.class)
    public void testDisplayValueOnTopWithEmptyStack() throws NotEnoughOperandsOnStackException {
        this.fakeStack.isEmpty = true;
        calc.displayValueOnTop();
    }

}

