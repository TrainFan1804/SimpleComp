package src.main.app;

// java import
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * The main class for the lexer. This is the that run the lexer. 
 * 
 * @author                              o.le
 * @version                             0.51
 * @since                               0.1
 */
public class SimpleLexer {
    
    public static void main(String[] args) throws IOException {

        SimpleLexer.runTerminal();
    }

    /**
     * Private constructor of the lexer because there is no need for a
     * lexer object.
     */
    private SimpleLexer() {}

    /**
     * Read the user input from the terminal.
     */
    private static void runTerminal() {
        

        try (InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(input)){
                    
            String line = " ";
            while (line.length() != 0) {
                        
                System.out.print("> ");
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
}
