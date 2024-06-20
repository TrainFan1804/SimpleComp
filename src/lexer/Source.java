package src.lexer;

// java import
import java.util.function.Predicate;

/**
 * This dataclass save the content that the lexer is reading. 
 * 
 * @author                              o.le
 * @version                             1.8
 * @since                               0.27
 */
public class Source {

    private String source;
    private int posCurrentChar;
    private int posStartOfLexeme;

    Source(String source) {
        
        this.source = source;
    }

    public void startLexeme() {

        this.posStartOfLexeme = this.posCurrentChar;
    }

    public char getNextChar() {

        return this.source.charAt(this.posCurrentChar++);
    }

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