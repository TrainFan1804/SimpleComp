package src.lexer;

/**
 * The class represent an exception that is thrown when the 
 * {@link LexerScannerOld} found an illegal character.
 * 
 * @author                              o.le
 * @version                             1.1
 * @since                               0.44
 */
@Deprecated
public class IllegalSymbolException extends Exception {

    public IllegalSymbolException(char symbol) {

        super("Illegal symbol: " + symbol);
    }
}
