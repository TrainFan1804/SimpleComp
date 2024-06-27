package src.lexer.tokens;

// custom import
import src.lexer.LexerScannerOld;
import src.lexer.TokenType;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.0
 * @since                               0.35
 */
public class Greater implements TokenAction {

    @Override
    public void action(LexerScannerOld scanner) {
        
        scanner.addTokenToList(scanner.checkNextChar('=') 
                                ? TokenType.GREATER_EQUAL
                                : TokenType.GREATER);
    }
}
