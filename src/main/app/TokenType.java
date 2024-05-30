package src.main.app;

/**
 * The enum class for the token type. This class is containing an unique
 * token.
 * 
 * @author                              o.le
 * @version                             1.4
 * @since                               0.4
 */
public enum TokenType {

    // tokens for math
    PLUS, MINUS, MULT, DIVISION,

    // tokens for booolean expresion
    EQUAL, EQUAL_EQUAL, BANG, BANG_EQUAL, GREATER, GREATER_EQUAL, 
    LESS, LESS_EQUAL,

    EOF;
}
