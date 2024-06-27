package src.lexer.tokens;

// custom import
import src.lexer.LexerScannerOld;

/**
 * The interface provide the functionality to start an action when
 * an specific token was scanned by the {@link LexerScannerOld}.
 * 
 * @author                              o.le
 * @version                             1.0
 * @since                               0.23
 */
public interface TokenAction {

    void action(LexerScannerOld scanner);
}
