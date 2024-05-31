package src.lexer;

// java import
import java.util.List;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * The main class for the lexer. This is the class that run the lexer.
 * 
 * @author                              o.le
 * @version                             1.0
 * @since                               0.1
 */
public class SimpleLexer {

    /**
     * Private constructor of the lexer because there is no need for a
     * lexer object.
     */
    private SimpleLexer() {}

    /**
     * Read the user input from the terminal.
     */
    public static void runTerminal() {
        

        try (InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(input)){
                    
            String line = " ";
            while (line.length() != 0) {
                        
                System.out.print(">> ");
                line = reader.readLine();
                SimpleLexer.run(line);
            }
        } catch (IOException e) {

            // TODO
        }
    }

    /**
     * Send the input to the {@link SimpleScanner} to scan for the
     * {@code Token} in the given input.
     * <p>
     * Right now this method will just print the tokens to the
     * console!!!
     * 
     * @param line                      The line that is currently
     *                                  scanning.
     */
    private static void run(String line) {

        SimpleScanner simpleScanner = new SimpleScanner(line);
        List<Token> l = simpleScanner.scanSource();
        for (Token t : l) {
            
            System.out.println(t);
        }
    }

    /**
     * When the lexer has an error. The kind of the error is not 
     * important.
     * 
     * @param message                   The error message to
     *                                  handle the problem.
     */
    static void error(String message) {

        SimpleLexer.printErrorReport(message);
    }

    /**
     * Print the error message that was given to 
     * {@link SimpleLexer#error(String)}.
     * 
     * @param message                   The error message that is
     *                                  printed to the screen,
     */
    private static void printErrorReport(String message) {

        System.err.println(">> [Error] " + message);
    }
}
