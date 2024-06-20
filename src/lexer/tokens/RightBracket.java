package src.lexer.tokens;

// custom import
import src.lexer.LexerScanner;
import src.lexer.TokenType;
import src.lexer.Lexer;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.4
 * @since                               0.32
 */
public class RightBracket implements TokenAction {

    @Override
    public void action(LexerScanner scanner) {
        
        if (scanner.checkBracketStack() == TokenType.LEFT_BRAC) {

            scanner.addTokenToList(TokenType.RIGHT_BRAC);
        } else {

            Lexer.error("Missing closing bracket!");
        }
    }
}
