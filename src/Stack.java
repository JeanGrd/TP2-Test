import java.util.EmptyStackException;

public interface Stack {
    /**
     * Tests if this stack is empty
     */
    public boolean isEmpty();
    /**
     * Returns the number of items in this stack.
     */
    public int getSize(); /**
     */
    public void push(double item);
    /**
     * Looks at the object at the top of this stack without removing it from the
     stack.
     */
    public double peek() throws EmptyStackException;
    /**
     * Removes the object at the top of this stack and returns
     * that object as the value of this function.
     * @throws EmptyStackException if this stack is empty.
     */
    public double pop() throws EmptyStackException;
}