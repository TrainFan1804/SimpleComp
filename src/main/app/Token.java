package src.main.app;

/**
 * The class for the token. This class is containing an unique token.
 * 
 * @author                              o.le
 * @version                             1.2
 * @since                               0.4
 */
public class Token {

    private String lexeme;
    private TokenType type;

    public Token(String lexeme, TokenType type) {

        this.lexeme = lexeme;
        this.type = type;
    }

    @Override
    public String toString() {

        return "lexeme: " + this.lexeme + ", type: " + this.type;
    }
}
