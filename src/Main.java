import java.text.ParseException;
import java.util.Scanner;

public class Main {
    private static final String EXIT_COMMAND = ".exit";
    public static void main(String[] args) {
        RPNNN parser = new RPNNN();
        Scanner scanner = new Scanner(System.in);

        System.out.println("RPN Calc");
        System.out.println("Type '.exit' at any time to quit");

        while (true) {
            System.out.println("Input a RPN expression");
            System.out.print("-> ");

            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase(EXIT_COMMAND)) {
                break;
            }

            try {
                double result = parser.parseAndDisplayResult(input);
                System.out.println(result);
            } catch (NotEnoughOperandsOnStackException e) {
                System.out.println("Not enough operands, Try again or type '.exit' to quit");
            } catch (DivideByZeroException e) {
                System.out.println("Divide by zero, Try again or type '.exit' to quit");
            } catch (ParseException e) {
                System.out.println("Invalid Expression, Try again or type '.exit' to quit");
            }
        }

        scanner.close();
    }
}
