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
 * @version                             0.42
 * @since                               0.1
 */
public class SimpleLexer {

    private SimpleLexer() {}

    public static void main(String[] args) throws IOException {

        SimpleLexer.runTerminal();
    }

    /**
     * Read the user input and transform the input in tokens for the
     * lexer.
     * 
     * @throws IOException
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

    private static void run(String line) {

        SimpleScanner simpleScanner = new SimpleScanner(line);
        List<Token> l = simpleScanner.scanSource();
        for (Token t : l) {
            
            System.out.println(t);
        }
    }
}
