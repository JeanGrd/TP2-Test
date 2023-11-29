import java.text.ParseException;

/**
 * Une implémentation de l'interface RPNParser qui interprète et évalue des expressions
 * en notation polonaise inversée (RPN).
 */
public class RPNParserImpl implements RPNParser {

    private final Calc calc;

    /**
     * Construit un nouveau parseur RPN avec une instance spécifique de Calc.
     *
     * @param calc L'instance de Calc à utiliser pour les calculs.
     */
    public RPNParserImpl(Calc calc) {
        this.calc = calc;
    }

    /**
     * Analyse une chaîne en notation polonaise inversée et retourne le résultat de l'évaluation.
     *
     * @param toParse La chaîne en notation RPN à analyser.
     * @return Le résultat de l'évaluation de l'expression RPN.
     * @throws NotEnoughOperandsOnStackException Si il n'y a pas assez d'opérandes pour une opération.
     * @throws DivideByZeroException Si une division par zéro est tentée.
     * @throws ParseException Si la chaîne contient des tokens non valides.
     */
    @Override
    public double parseAndDisplayResult(String toParse) throws NotEnoughOperandsOnStackException, DivideByZeroException, ParseException {
        toParse = toParse.trim().replaceAll("\\s+", " ");
        String[] tokens = toParse.split(" ");

        for (String token : tokens) {
            switch (token) {
                case "+" -> calc.add();
                case "-" -> calc.subtract();
                case "*" -> calc.multiply();
                case "/" -> calc.divide();
                default -> {
                    try {
                        calc.enterValue(Double.parseDouble(token));
                    } catch (NumberFormatException e) {
                        throw new ParseException("Invalid token: " + token, toParse.indexOf(token));
                    }
                }
            }
        }

        return calc.displayValueOnTop();
    }
}
