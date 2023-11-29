/**
 * Une implémentation fictive de l'interface Calc destinée à être utilisée dans des tests.
 * Cette classe ne réalise pas de calculs réels mais simule les opérations de la calculatrice
 * en manipulant une valeur fictive pour tester le comportement des consommateurs de l'interface Calc.
 */
public class CalcFakeImpl implements Calc {
    private double lastValue;

    /**
     * Simule l'entrée d'une valeur dans la calculatrice.
     *
     * @param value La valeur à entrer.
     */
    @Override
    public void enterValue(double value) {
        this.lastValue = value;
    }

    /**
     * Simule une opération d'addition sur la valeur actuelle.
     *
     * @throws NotEnoughOperandsOnStackException Si la condition requise pour l'opération n'est pas remplie.
     */
    @Override
    public void add() throws NotEnoughOperandsOnStackException {
        this.lastValue += 2;
    }

    /**
     * Simule une opération de soustraction sur la valeur actuelle.
     *
     * @throws NotEnoughOperandsOnStackException Si la condition requise pour l'opération n'est pas remplie.
     */
    @Override
    public void subtract() throws NotEnoughOperandsOnStackException {
        this.lastValue -= 2;
    }

    /**
     * Simule une opération de multiplication sur la valeur actuelle.
     *
     * @throws NotEnoughOperandsOnStackException Si la condition requise pour l'opération n'est pas remplie.
     */
    @Override
    public void multiply() throws NotEnoughOperandsOnStackException {
        this.lastValue *= 2;
    }

    /**
     * Simule une opération de division sur la valeur actuelle.
     *
     * @throws NotEnoughOperandsOnStackException Si la condition requise pour l'opération n'est pas remplie.
     * @throws DivideByZeroException Si une division par zéro est simulée.
     */
    @Override
    public void divide() throws NotEnoughOperandsOnStackException, DivideByZeroException {
        if (lastValue == 0) {
            throw new DivideByZeroException();
        }
        this.lastValue /= 2;
    }

    /**
     * Retourne la dernière valeur manipulée, simulant l'affichage de la valeur au sommet de la pile.
     *
     * @return La dernière valeur manipulée.
     * @throws NotEnoughOperandsOnStackException Si la condition requise pour l'opération n'est pas remplie.
     */
    @Override
    public double displayValueOnTop() throws NotEnoughOperandsOnStackException {
        return this.lastValue;
    }
}
