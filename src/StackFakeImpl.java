import java.util.EmptyStackException;

public class StackFakeImpl implements Stack {

    int size;
    boolean isEmpty;
    double topValue;

    public StackFakeImpl(boolean isEmpty, int size, double topValue) {
        this.isEmpty = isEmpty;
        this.size = size;
        this.topValue = topValue;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void push(double item) {
        topValue = item;
    }

    @Override
    public double peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topValue;
    }

    @Override
    public double pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topValue;
    }
}
