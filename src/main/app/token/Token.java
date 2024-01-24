package src.main.app.token;

/**
 * The class for wrapping up the tokens
 * 
 * @author o.le
 * @version 0.2
 * @since 0.1
 */
public class Token {

    private final TokenType type;
    private final String lexeme;
    private final Object literal;
    private final int line;

    /**
     * Create a token with specific attributes
     * 
     * @param type The type of the token
     * @param lexeme The lexeme of the token
     * @param literal The lexeme for literal objects
     * @param line The line of the token
     */
    public Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    @Override
    public String toString() {
        return "type: " + this.type + " lexeme: " + this.lexeme + " iteral: " + this.literal + "\n";
    }
}
