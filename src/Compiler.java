package src;

// java import
import java.io.File;
// custom import
import src.lexer.Lexer;
import src.parser.Parser;

/**
 * The main class for the compiler. This is the class that run the compiler.
 * <p>
 * This class is designed as a <b>Singleton<b>.
 * 
 * @author                              o.le
 * @version                             0.3
 * @since                               0.50
 */
public class Compiler {

    private static Compiler compiler;

    private Lexer lexer;
    private Parser parser;

    /**
     * Create an compiler object.
     * 
     * @return                          The compiler object.
     */
    public static Compiler createCompiler() {

        if (Compiler.compiler == null) {

            Compiler.compiler = new Compiler();
        }

        return Compiler.compiler;
    }
    /**
     * Create a new compiler.
     */
    private Compiler() { 

        this.lexer = Lexer.createLexer();
        this.parser = Parser.createParser();
    }

    /**
     * Start the compiler.
     * 
     * @param args                      The run arguments. Compile the given
     *                                  file or read the terminal when there
     *                                  is no argument.
     */
    public void run(String[] args) {

        if (args.length > 0) {

            this.lexer.runFile(new File(args[0]));
        } else {

            this.lexer.runTerminal();
        }
    }
}
