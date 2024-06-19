package src.lexer;

/**
 * This dataclass save the content that the lexer is reading. 
 * 
 * @author                              o.le
 * @version                             1.3
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
    void checkForMultipleExpression() {

        if (this.isAtEnd()) {

            return;
        }

        while (this.source.charAt(posCurrentChar) >= '0' 
                && this.source.charAt(posCurrentChar) <= '9') {
            
            posCurrentChar++;

            if (this.isAtEnd()) {
                
                return;
            }
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
