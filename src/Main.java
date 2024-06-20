package src;

// java import
import java.io.File;
import java.io.IOException;
// custom import
import src.lexer.Lexer;

/**
 * This class run the interpreter.
 * 
 * @author                              o.le
 * @version                             0.42
 * @since                               0.1
 */
public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {

            Lexer.createLexer().runFile(new File(args[0]));
        } else {

            Lexer.createLexer().runTerminal();
        }
    }
}
