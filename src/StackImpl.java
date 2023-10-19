import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackImpl implements Stack {
    private ArrayList<Double> stackList = new ArrayList<Double>();

    @Override
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    @Override
    public int getSize() {
        return stackList.size();
    }

    @Override
    public void push(double item) {
        stackList.add(item);
    }

    @Override
    public double peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackList.get(stackList.size() - 1);
    }

    @Override
    public double pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackList.remove(stackList.size() - 1);
    }
}

