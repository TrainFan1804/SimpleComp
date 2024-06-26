package src.lexer;

// java import
import java.util.List;
import java.util.LinkedList;

/**
 * @author                              o.le
 * @version                             1.0
 * @since                               0.49
 */
public class ScannedTokens {

    private static List<Token> scannedTokens = new LinkedList<>();

    static void addToList(List<Token> token) {

        ScannedTokens.scannedTokens.addAll(token);
    }

    public static List<Token> getScannedTokens() {

        return ScannedTokens.scannedTokens;
    }
}
