package src.main.app;

import java.util.LinkedList;
import java.util.List;

/**
 * The class for the scanner. This class is scanning the given code.
 * 
 * @author                              o.le
 * @version                             0.4
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
                this.addTokenToList(this.checkForDoubleExpresion('=')
                                        ? TokenType.EQUAL_EQUAL 
                                        : TokenType.EQUAL);
                break;
            case '!': 
                this.addTokenToList(this.checkForDoubleExpresion('=')
                                        ? TokenType.BANG_EQUAL
                                        : TokenType.BANG);
                break;
            case '>':
                this.addTokenToList(this.checkForDoubleExpresion('=')
                                        ? TokenType.GREATER_EQUAL 
                                        : TokenType.GREATER);
                break;
            case '<':
                this.addTokenToList(this.checkForDoubleExpresion('=')
                                        ? TokenType.LESS_EQUAL 
                                        : TokenType.LESS);
                break;
            case ' ':
            case '\r':
            case '\t':
                break;
            default:
                System.err.println("Error. Wrong symbol!");
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
    private boolean checkForDoubleExpresion(char expectedChar) {

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
     * Check if the scanner has read the whole source.
     * 
     * @return                          {@code true} when the scanner
     *                                  completed the scan else 
     *                                  {@code false}.
     */
    private boolean isAtEnd() {

        return this.posCurrentChar == this.source.length();
    }
}