package src.lexer.tokens;

// custom import
import src.lexer.LexerScanner;
import src.lexer.TokenType;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.0
 * @since                               0.34
 */
public class Literal implements TokenAction {

    @Override
    public void action(LexerScanner scanner) {
        
        scanner.checkForMultipleExpression();
        scanner.addTokenToList(TokenType.LITERAL);
    }

}
