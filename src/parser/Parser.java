package src.parser;

// java import
import java.util.List;
// custom import
import src.lexer.Token;

/**
 * The main class for the parser. This is the class that run the parser.
 * <p>
 * This class is designed as a <b>Singleton<b>.
 * 
 * @author                              o.le
 * @version                             0.6
 * @since                               0.10
 */
public class Parser {

    private static Parser parser;

    private List<Token> tokenList;

    /**
     * Create an parser object.
     * 
     * @return                          The parser object.
     */
    public static Parser createParser() {

        if (Parser.parser == null) {

            Parser.parser = new Parser();
        }

        return Parser.parser;
    }
    /**
     * Create a new parser.
     */
    private Parser() { }

    public void fillData(List<Token> list) {

        this.tokenList = list;
        System.out.println(tokenList.toString());
    }
}
