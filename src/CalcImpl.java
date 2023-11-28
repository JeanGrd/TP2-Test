public class CalcImpl implements Calc {

    private Stack stack;

    public CalcImpl (Stack s) {
        this.stack = s;
    }

    @Override
    public void enterValue(double value) {
        stack.push(value);
    }

    @Override
    public void add() throws NotEnoughOperandsOnStackException {
        if (stack.getSize() < 2) {
            throw new NotEnoughOperandsOnStackException();
        }
        double value1 = stack.pop();
        double value2 = stack.pop();
        stack.push(value2 + value1);
    }

    @Override
    public void subtract() throws NotEnoughOperandsOnStackException {
        if (stack.getSize() < 2) {
            throw new NotEnoughOperandsOnStackException();
        }
        double value1 = stack.pop();
        double value2 = stack.pop();
        stack.push(value2 - value1);
    }

    @Override
    public void multiply() throws NotEnoughOperandsOnStackException {
        if (stack.getSize() < 2) {
            throw new NotEnoughOperandsOnStackException();
        }
        double value1 = stack.pop();
        double value2 = stack.pop();
        stack.push(value2 * value1);
    }

    @Override
    public void divide() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        if (stack.getSize() < 2) {
            throw new NotEnoughOperandsOnStackException();
        }
        double value1 = stack.pop();
        double value2 = stack.pop();
        if (value1 == 0) {
            throw new DivideByZeroException();
        }
        stack.push(value2 / value1);
    }

    @Override
    public double displayValueOnTop() throws NotEnoughOperandsOnStackException {
        if (stack.isEmpty()) {
            throw new NotEnoughOperandsOnStackException();
        }
        return stack.peek();
    }
}
