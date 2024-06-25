package src.lexer;

// java import
import java.util.function.Predicate;

/**
 * This dataclass save the content that the lexer is reading. 
 * 
 * @author                              o.le
 * @version                             1.13
 * @since                               0.27
 */
public class Source {

    private String source;
    private int posCurrentChar;
    private int posStartOfLexeme;

    Source(String source) {
        
        this.source = source;
        this.posCurrentChar = 0;
    }

    /**
     * Start the scanning of an new lexeme.
     */
    public void startLexeme() {

        this.posStartOfLexeme = this.posCurrentChar;
    }

    /**
     * Get the next char of the source pointer.
     * 
     * @return                          The next char.
     */
    public char getNextChar() {

        return this.source.charAt(this.posCurrentChar++);
    }

    /**
     * Set the char pointer of the source one position back.
     */
    public void setCharBack() {

        this.posCurrentChar--;
    }

    /**
     * Get the lexeme that is currently scanning.
     * 
     * @return                          The string representation of
     *                                  the lexeme.
     */
    public String getLexeme() {

        return this.source.substring(this.posStartOfLexeme,
                                        this.posCurrentChar);
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
    boolean checkForDoubleExpression(char expectedChar) {

        if (!this.isAtEnd() 
                && this.source.charAt(this.posCurrentChar) 
                    == expectedChar) {
            
            this.posCurrentChar++;
            return true;
        }
        
        return false;
    }

    /**
     * Check if the current character is an literal with multiple character.
     * 
     * @param condition                 The condition that is checked.
     */
    void checkForMultipleExpression(Predicate<Character> condtions) {

        while (!this.isAtEnd() 
                && condtions.test(this.source.charAt(this.posCurrentChar))) {
            
            posCurrentChar++;
        }
    }

    /**
     * Check if the source has reached the end.
     * 
     * @return                          {@code true} when the source
     *                                  is at the end {@code false}.
     */
    public boolean isAtEnd() {

        return this.posCurrentChar >= this.source.length();
    }
}
