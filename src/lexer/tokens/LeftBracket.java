package src.lexer.tokens;

// custom import
import src.lexer.LexerScanner;
import src.lexer.TokenType;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.2
 * @since                               0.32
 */
public class LeftBracket implements TokenAction {

    @Override
    public void action(LexerScanner scanner) {
        
        scanner.pushLeftBracketToStack(TokenType.LEFT_BRAC);
        scanner.addTokenToList(TokenType.LEFT_BRAC);
    }
}
