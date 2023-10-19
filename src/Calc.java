public interface Calc {
    /**
     * pushes a value on the RPN stack
     * @param value
     */
    void enterValue(double value);
    /**
     * adds the two last values, replacing them with the result on top
     * @throws NotEnoughOperandsOnStackException
     */
    void add() throws NotEnoughOperandsOnStackException;
    /**
     * subtracts the two last values, replacing them with the result on top
     * @throws NotEnoughOperandsOnStackException
     */
    void subtract() throws NotEnoughOperandsOnStackException;
    /**
     * multiplies the two last values, replacing them with the result on top
     * @throws NotEnoughOperandsOnStackException
     */
    void multiply() throws NotEnoughOperandsOnStackException;
    /**
     * divides the two last values, replacing them with the result on top
     * @throws NotEnoughOperandsOnStackException
     * @throws DivideByZeroException
     */
    void divide() throws NotEnoughOperandsOnStackException, DivideByZeroException;
    /**
     * displays the last value on top of the stack
     * @return the last value
     * @throws NotEnoughOperandsOnStackException
     */
    double displayValueOnTop() throws NotEnoughOperandsOnStackException;
}