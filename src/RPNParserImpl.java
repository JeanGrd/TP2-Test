import java.text.ParseException;
import java.util.Stack;

public class RPNParserImpl implements RPNParser {

    @Override
    public double parseAndDisplayResult(String toParse) throws NotEnoughOperandsOnStackException, DivideByZeroException, ParseException {
        toParse = toParse.trim().replaceAll("\\s+", " ");

        Stack<Double> stack = new Stack<>();
        String[] tokens = toParse.split(" ");

        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    if (stack.size() < 2) throw new NotEnoughOperandsOnStackException();
                    stack.push(stack.pop() + stack.pop());
                }
                case "-" -> {
                    if (stack.size() < 2) throw new NotEnoughOperandsOnStackException();
                    double subtractor = stack.pop();
                    stack.push(stack.pop() - subtractor);
                }
                case "*" -> {
                    if (stack.size() < 2) throw new NotEnoughOperandsOnStackException();
                    stack.push(stack.pop() * stack.pop());
                }
                case "/" -> {
                    if (stack.size() < 2) throw new NotEnoughOperandsOnStackException();
                    double divisor = stack.pop();
                    if (divisor == 0) throw new DivideByZeroException();
                    stack.push(stack.pop() / divisor);
                }
                default -> {
                    try {
                        stack.push(Double.parseDouble(token));
                    } catch (NumberFormatException e) {
                        throw new ParseException("Invalid token: " + token, toParse.indexOf(token));
                    }
                }
            }
        }

        if (stack.size() != 1) {
            throw new NotEnoughOperandsOnStackException();
        }
        return stack.pop();
    }
}
