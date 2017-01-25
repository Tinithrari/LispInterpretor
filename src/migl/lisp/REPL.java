package migl.lisp;

import java.util.Scanner;

/**
 * A very basic Read Eval Print Loop for your interpreter.
 * 
 * It is make available to let you play with your work and even to try running
 * real lisp program with it.
 * 
 * @author leberre
 *
 */
public class REPL {

    private REPL() {
        // to prevent instantiation
    }

    public static void main(String[] args) {
        Lisp lisp = LispFactory.makeIntepreter();
        try (Scanner scanner = new Scanner(System.in, "UTF8")) {
            System.out.println("My super own Lisp/Scheme interpreter 2017");
            System.out.println("Enter a valid Lisp expression followed by Enter. type 'quit' to exit.");
            System.out.print("> ");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if ("quit".equalsIgnoreCase(line)) {
                    System.out.println("Bye.");
                    return;
                }
                try {
                    System.out.println(lisp.eval(line));
                } catch (LispError le) {
                    System.out.println("Error: " + le.getMessage());
                }
                System.out.print("> ");
            }
        }
    }
}
