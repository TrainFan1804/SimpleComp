package src.lexer.tokens;

// custom import
import src.lexer.LexerScannerOld;
import src.lexer.TokenType;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.1
 * @since                               0.34
 */
public class Literal implements TokenAction {

    @Override
    public void action(LexerScannerOld scanner) {
        
        scanner.checkForMultipleExpression(c -> c >= '0' && c <= '9');
        scanner.addTokenToList(TokenType.LITERAL);
    }
}
