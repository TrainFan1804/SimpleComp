package src.main.app.token;

/**
 * All tokens for the "ant" language
 * 
 * @author o.le
 * @version 0.2
 * @since 0.1
 */
public enum TokenType {
    // Single-character tokens
    DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

    // One or two character tokens
    EQUAL,

    // Literals
    IDENTIFIER, STRING, NUMBER,

    // Keywords
    PRINT, ANT,

    EOF
}
