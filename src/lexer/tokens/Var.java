package src.lexer.tokens;

// custom import
import src.lexer.LexerScanner;
import src.lexer.TokenType;
import src.lexer.Lexer;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.2
 * @since                               0.39
 */
public class Var implements TokenAction {

    @Override
    public void action(LexerScanner scanner) {
        
        if (scanner.checkNextChar('a') && scanner.checkNextChar('r')) {

            scanner.addTokenToList(TokenType.VAR);
        } else {

            Lexer.error("Do you mean 'var' instead of '" + scanner.getCurrentLexeme() + "'?");
        }
    }
}
