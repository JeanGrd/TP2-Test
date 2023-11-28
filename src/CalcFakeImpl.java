public class CalcFakeImpl implements Calc {

    private Stack stack;
    private double lastResult;

    public CalcFakeImpl(Stack stack, double lastResult) {
        this.stack = stack;
        this.lastResult = lastResult;
    }

    @Override
    public void enterValue(double value) {
        stack.push(value);
    }

    @Override
    public void add() throws NotEnoughOperandsOnStackException {
    }

    @Override
    public void subtract() throws NotEnoughOperandsOnStackException {
    }

    @Override
    public void multiply() throws NotEnoughOperandsOnStackException {
    }

    @Override
    public void divide() throws NotEnoughOperandsOnStackException, DivideByZeroException {
    }

    @Override
    public double displayValueOnTop() throws NotEnoughOperandsOnStackException {
        if (stack.isEmpty()) {
            throw new NotEnoughOperandsOnStackException();
        }
        return lastResult;
    }
}
