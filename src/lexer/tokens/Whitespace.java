package src.lexer.tokens;

// custom import
import src.lexer.LexerScannerOld;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.0
 * @since                               0.45
 */
@Deprecated
public class Whitespace implements TokenAction {

    @Override
    public void action(LexerScannerOld scanner) { }
}
