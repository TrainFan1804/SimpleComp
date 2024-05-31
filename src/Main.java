package src;

// java import
import java.io.IOException;
// custom import
import src.lexer.SimpleLexer;

/**
 * The class run the interpreter.
 * 
 * @author                              o.le
 * @version                             0.14
 * @since                               0.1
 */
public class Main {

    public static void main(String[] args) throws IOException {

        SimpleLexer.runTerminal();
    }
}
