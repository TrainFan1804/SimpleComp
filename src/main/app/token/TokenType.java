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
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

    // One or two character tokens
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // Literals
    IDENTIFIER, STRING, NUMBER,

    // Keywords
    PRINT, VAR, FOR, IF, ELSE, WHILE, RETURN, DEF,

    EOF
}
