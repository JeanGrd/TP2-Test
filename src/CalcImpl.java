/**
 * Une implémentation de l'interface Calc utilisant un objet Stack.
 * Cette classe fournit des méthodes pour effectuer des opérations arithmétiques
 * de base telles que l'addition, la soustraction, la multiplication, et la division,
 * en suivant le principe de la notation polonaise inversée (RPN).
 */
public class CalcImpl implements Calc {

    private Stack stack;

    /**
     * Constructeur pour CalcImpl.
     *
     * @param s L'objet Stack à utiliser pour les opérations de calcul.
     */
    public CalcImpl (Stack s) {
        this.stack = s;
    }

    /**
     * Ajoute une valeur à la pile.
     *
     * @param value La valeur à ajouter.
     */
    @Override
    public void enterValue(double value) {
        stack.push(value);
    }

    /**
     * Effectue une opération d'addition sur les deux dernières valeurs de la pile.
     *
     * @throws NotEnoughOperandsOnStackException Si la pile ne contient pas assez d'opérandes.
     */
    @Override
    public void add() throws NotEnoughOperandsOnStackException {
        if (stack.getSize() < 2) {
            throw new NotEnoughOperandsOnStackException();
        }
        double value1 = stack.pop();
        double value2 = stack.pop();
        stack.push(value2 + value1);
    }

    /**
     * Effectue une opération de soustraction sur les deux dernières valeurs de la pile.
     *
     * @throws NotEnoughOperandsOnStackException Si la pile ne contient pas assez d'opérandes.
     */
    @Override
    public void subtract() throws NotEnoughOperandsOnStackException {
        if (stack.getSize() < 2) {
            throw new NotEnoughOperandsOnStackException();
        }
        double value1 = stack.pop();
        double value2 = stack.pop();
        stack.push(value2 - value1);
    }

    /**
     * Effectue une opération de multiplication sur les deux dernières valeurs de la pile.
     *
     * @throws NotEnoughOperandsOnStackException Si la pile ne contient pas assez d'opérandes.
     */
    @Override
    public void multiply() throws NotEnoughOperandsOnStackException {
        if (stack.getSize() < 2) {
            throw new NotEnoughOperandsOnStackException();
        }
        double value1 = stack.pop();
        double value2 = stack.pop();
        stack.push(value2 * value1);
    }

    /**
     * Effectue une opération de division sur les deux dernières valeurs de la pile.
     *
     * @throws NotEnoughOperandsOnStackException Si la pile ne contient pas assez d'opérandes.
     * @throws DivideByZeroException Si une tentative de division par zéro est détectée.
     */
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

    /**
     * Renvoie la valeur actuellement au sommet de la pile.
     *
     * @return La valeur au sommet de la pile.
     * @throws NotEnoughOperandsOnStackException Si la pile est vide.
     */
    @Override
    public double displayValueOnTop() throws NotEnoughOperandsOnStackException {
        if (stack.isEmpty()) {
            throw new NotEnoughOperandsOnStackException();
        }
        return stack.peek();
    }
}
