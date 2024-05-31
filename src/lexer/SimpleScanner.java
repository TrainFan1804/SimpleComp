package src.lexer;

// java import
import java.util.List;
import java.util.LinkedList;

/**
 * The class for the scanner. This class is scanning the given code.
 * 
 * @author                              o.le
 * @version                             1.2
 * @since                               0.4
 */
public class SimpleScanner {

    private String source;
    private int posStartOfLexeme;
    private int posCurrentChar;

    private List<Token> containedTokens;

    /**
     * Create a new simple scanner.
     * 
     * @param source                    The source the scanner is
     *                                  scanning.
     */
    public SimpleScanner(String source) {

        this.source = source;
        this.containedTokens = new LinkedList<>();
    }

    /**
     * The simple scanner is scanning through his saved source.
     * 
     * @return                          The list of all {@link Token}
     *                                  that was in the source.
     */
    public List<Token> scanSource() {

        while(!this.isAtEnd()) {

            this.posStartOfLexeme = this.posCurrentChar;
            this.identifyToken();
        }

        this.containedTokens.add(new Token("", TokenType.EOF));
        return this.containedTokens;
    }

    /**
     * Identify a token in the source.
     */
    private void identifyToken() {

        char currentChar = this.source.charAt(posCurrentChar++);

        switch (currentChar) {
            case '+': this.addTokenToList(TokenType.PLUS); break;
            case '-': this.addTokenToList(TokenType.MINUS); break;
            case '*': this.addTokenToList(TokenType.MULT); break;
            case '/': this.addTokenToList(TokenType.DIVISION); break;
            case '=':
                this.addTokenToList(this.checkForDoubleExpression('=')
                                        ? TokenType.EQUAL_EQUAL 
                                        : TokenType.EQUAL);
                break;
            case '!': 
                this.addTokenToList(this.checkForDoubleExpression('=')
                                        ? TokenType.BANG_EQUAL
                                        : TokenType.BANG);
                break;
            case '>':
                this.addTokenToList(this.checkForDoubleExpression('=')
                                        ? TokenType.GREATER_EQUAL 
                                        : TokenType.GREATER);
                break;
            case '<':
                this.addTokenToList(this.checkForDoubleExpression('=')
                                        ? TokenType.LESS_EQUAL 
                                        : TokenType.LESS);
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                this.checkForMultipleExpression();
                this.addTokenToList(TokenType.LITERAL);
                break;
            // ignore all different types of whitespaces
            case ' ':
            case '\r':
            case '\t':
                break;
            default:
                
                System.err.println("Invalid character: " + currentChar);
                break;
        }
    }

    /**
     * Add a token to the list that save all {@link Token}.
     * 
     * @param lexeme                    The lexeme of the new token.
     */
    private void addTokenToList(TokenType lexeme) {

        String s = this.source.substring(this.posStartOfLexeme, 
                                            this.posCurrentChar);
        this.containedTokens.add(new Token(s, lexeme));
    }

    /**
     * Check if the current token include two characters. E.g. '!=' 
     * or '=='.
     * 
     * @param expectedChar              The character that can be after
     *                                  the current character.
     * @return                          {@code true} when the current
     *                                  token has two character {@code false}
     *                                  when the token has only one
     *                                  character.
     */
    private boolean checkForDoubleExpression(char expectedChar) {

        if (this.isAtEnd()) {

            return false;
        }
        if (this.source.charAt(this.posCurrentChar) != expectedChar) {

            return false;
        }

        this.posCurrentChar++;
        return true;
    }

    /**
     * When an integer value appears the lexer is checking if the
     * following character are also integer. 
     * <p>
     * When the following characters ARE integer the 
     * {@link SimpleScanner#posCurrentChar} is updating.
     */
    private void checkForMultipleExpression() {

        while (this.source.charAt(posCurrentChar) >= '0' 
                && this.source.charAt(posCurrentChar) <= '9') {
            
            posCurrentChar++;

            if (this.isAtEnd()) {
                
                return;
            }
        }
    }

    /**
     * Check if the scanner has read the whole source.
     * 
     * @return                          {@code true} when the scanner
     *                                  completed the scan else 
     *                                  {@code false}.
     */
    private boolean isAtEnd() {

        return this.posCurrentChar >= this.source.length();
    }
}
