import java.text.ParseException;
import java.util.Stack;

public class RPNNN implements RPNParser {

    private CalcImpl calc;

    public RPNNN() {
        this.calc = new CalcImpl(new StackImpl());
    }

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
