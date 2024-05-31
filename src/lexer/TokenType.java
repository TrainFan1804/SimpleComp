package src.lexer;

/**
 * The enum class for the token type. This class is containing an unique
 * token.
 * 
 * @author                              o.le
 * @version                             1.8
 * @since                               0.4
 */
public enum TokenType {

    // tokens for special character
    LEFT_SQBRA, RIGHT_SQBRA, LEFT_BRAC, RIGHT_BRAC,

    // tokens for math
    PLUS, MINUS, MULT, DIVISION,

    // tokens for booolean expresion
    EQUAL, EQUAL_EQUAL, BANG, BANG_EQUAL, GREATER, GREATER_EQUAL, 
    LESS, LESS_EQUAL,

    // tokens for keywords
    PRINT, IF, ELSE, FUNCTION, RETURN,

    LITERAL,

    EOF;
}
