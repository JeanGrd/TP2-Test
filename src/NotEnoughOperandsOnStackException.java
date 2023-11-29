/**
 * Exception personnalisée indiquant qu'il n'y a pas assez d'opérandes sur la pile
 * pour effectuer l'opération demandée.
 * Cette exception est levée lorsqu'une opération dans CalcImpl nécessite plus d'opérandes
 * qu'il n'y en a actuellement sur la pile.
 */
public class NotEnoughOperandsOnStackException extends Exception {
    /**
     * Constructeur par défaut pour NotEnoughOperandsOnStackException.
     * Initialise l'exception avec un message d'erreur par défaut.
     */
    public NotEnoughOperandsOnStackException() {
        super("Not enough operands on stack for the operation.");
    }
}

