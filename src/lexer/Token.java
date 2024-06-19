package src.lexer;

/**
 * The class for the token. This class is containing an unique token.
 * 
 * @author                              o.le
 * @version                             1.4
 * @since                               0.4
 */
public class Token {

    private String lexeme;
    private TokenType type;

    /**
     * Create a new token.
     * 
     * @param lexeme                    The lexeme of the new token.
     *                                  The lexeme is the exact symbol
     *                                  that represent the token. 
     * @param type                      The type of the new token.
     */
    public Token(String lexeme, TokenType type) {

        this.lexeme = lexeme;
        this.type = type;
    }

    @Override
    public String toString() {

        return "lexeme: " + this.lexeme + " type: " + this.type;
    }
}
