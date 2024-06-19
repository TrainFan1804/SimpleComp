package src.lexer.tokens;

// custom import
import src.lexer.LexerScanner;
import src.lexer.TokenType;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.0
 * @since                               0.32
 */
public class Plus implements TokenAction {

    @Override
    public void action(LexerScanner scanner) {
        
        scanner.addTokenToList(TokenType.PLUS);
    }
}
