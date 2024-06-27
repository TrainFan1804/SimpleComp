package src.lexer.tokens;

// custom import
import src.lexer.LexerScannerOld;
import src.lexer.TokenType;
import src.lexer.Lexer;

/**
 * This class represent an specific token.
 * 
 * @author                              o.le
 * @version                             1.3
 * @since                               0.39
 */
public class Var implements TokenAction {

    @Override
    public void action(LexerScannerOld scanner) {
        
        if (scanner.checkNextChar('a') && scanner.checkNextChar('r')) {

            scanner.addTokenToList(TokenType.VAR);
            scanner.checkForMultipleExpression(c -> c >= 'a' && c <= 'z');
            scanner.addTokenToList(TokenType.LITERAL);
        } else {

            Lexer.error("Do you mean 'var' instead of '" + scanner.getCurrentLexeme() + "'?");
        }
    }
}
