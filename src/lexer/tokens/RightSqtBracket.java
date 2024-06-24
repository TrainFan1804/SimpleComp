package src.lexer.tokens;

// custom import
import src.lexer.LexerScanner;
import src.lexer.TokenType;
import src.lexer.Lexer;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.1
 * @since                               0.32
 */
public class RightSqtBracket implements TokenAction {

    @Override
    public void action(LexerScanner scanner) {
        
        if (scanner.checkLeftBracketStack() == TokenType.LEFT_SQBRA) {

            scanner.addTokenToList(TokenType.RIGHT_SQBRA);
        } else {

            Lexer.error("Missing opening '[' bracket!");
        }
    }
}
