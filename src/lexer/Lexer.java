package src.lexer;

// java import
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * The main class for the lexer. This is the class that run the lexer.
 * 
 * @author                              o.le
 * @version                             1.8
 * @since                               0.1
 */
public class Lexer {

    private static Lexer simpleLexer;

    /**
     * Private constructor of the lexer because the lexer object is
     * created by the method {@link Lexer#createLexer()}.
     * <p>
     * This class is designed as a <b>Singleton<b>.
     */
    private Lexer() {}

    /**
     * Create an lexer object.
     * 
     * @return                          The lexer object.
     */
    public static Lexer createLexer() {

        if (simpleLexer == null) {

            Lexer.simpleLexer = new Lexer();
        }

        return Lexer.simpleLexer;
    }

    /**
     * Read through an file and read the tokens.
     * 
     * @param filePath                  The file that the lexer read.
     */
    public void runFile(File filePath) {

        try (FileReader input = new FileReader(filePath);
                BufferedReader reader = new BufferedReader(input)) {
            
            String line = "";
            do {

                line = reader.readLine();
                this.run(line);
            } while(!line.isEmpty());
        } catch (IOException e) {

            // TODO
        }
    }

    /**
     * Read the user input from the terminal.
     */
    public void runTerminal() {
        
        try (InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(input)){
                    
            String line = "";
            do {

                System.out.print(">> ");
                line = reader.readLine();
                this.run(line);
            } while (!line.isEmpty());
        } catch (IOException e) {

            // TODO
        }
    }

    /**
     * Send the input to the {@link LexerScanner} to scan for the
     * {@code Token} in the given input.
     * <p>
     * Right now this method will just print the tokens to the
     * console!!!
     * 
     * @param line                      The line that is currently
     *                                  scanning.
     */
    private void run(String line) {

        Source source = new Source(line);
        LexerScanner simpleScanner = new LexerScanner(source);
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

        Lexer.printErrorReport(message);
    }

    /**
     * Print the error message that was given to 
     * {@link Lexer#error(String)}.
     * 
     * @param message                   The error message that is
     *                                  printed to the screen,
     */
    private static void printErrorReport(String message) {

        System.err.println(">> [Error] " + message);
    }
}
