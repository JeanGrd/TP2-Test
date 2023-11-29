import java.text.ParseException;
import java.util.Scanner;

/**
 * Point d'entrée principal pour une application de calculatrice en notation polonaise inversée (RPN).
 * Cette application lit les expressions RPN de l'utilisateur, les évalue et affiche les résultats.
 * L'utilisateur peut quitter l'application en tapant '.exit'.
 */
public class Main {
    private static final String EXIT_COMMAND = ".exit";
    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        CalcImpl calc = new CalcImpl(stack);
        RPNParserImpl parser = new RPNParserImpl(calc);
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
