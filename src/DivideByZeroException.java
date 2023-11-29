/**
 * Exception personnalisée indiquant une tentative de division par zéro.
 * Cette exception est levée lorsqu'une opération de division dans CalcImpl
 * détecte un diviseur égal à zéro.
 */
public class DivideByZeroException extends Exception {
    /**
     * Constructeur par défaut pour DivideByZeroException.
     * Initialise l'exception avec un message d'erreur par défaut.
     */
    public DivideByZeroException() {
        super("Cannot divide by zero.");
    }
}
