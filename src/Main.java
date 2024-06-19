package src;

// java import
import java.io.File;
import java.io.IOException;
// custom import
import src.lexer.SimpleLexer;

/**
 * The class run the interpreter.
 * 
 * @author o.le
 * @version 0.21
 * @since 0.1
 */
public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {

            SimpleLexer.runFile(new File(args[0]));
        } else {

            SimpleLexer.runTerminal();
            // File f = new File("src/Test");
            // SimpleLexer.runFile(f);
        }
    }
}
