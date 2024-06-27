package src.lexer.tokens;

// custom import
import src.lexer.LexerScannerOld;
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
    public void action(LexerScannerOld scanner) {
        
        if (scanner.checkLeftBracketStack() == TokenType.LEFT_BRAC) {

            scanner.addTokenToList(TokenType.RIGHT_BRAC);
        } else {

            Lexer.error("Missing opening '{' bracket!");
        }
    }
}
