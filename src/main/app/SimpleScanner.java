package src.main.app;

import java.util.LinkedList;
import java.util.List;

/**
 * The class for the scanner. This class is scanning the given code.
 * 
 * @author                              o.le
 * @version                             0.31
 * @since                               0.4
 */
public class SimpleScanner {

    private String source;
    private int posStartOfLexeme;
    private int posCurrentChar;

    private List<Token> containedTokens;

    public SimpleScanner(String s) {

        this.source = s;
        this.containedTokens = new LinkedList<>();
    }

    public List<Token> scanSource() {

        while(!this.isAtEnd()) {

            this.posStartOfLexeme = this.posCurrentChar;
            this.identifyToken();
        }

        this.containedTokens.add(new Token("", TokenType.EOF));
        return this.containedTokens;
    }

    private void identifyToken() {

        char currentChar = this.source.charAt(posCurrentChar++);

        switch (currentChar) {
            case '=': this.addTokenToList(TokenType.EQUAL); break;
            case '+': this.addTokenToList(TokenType.PLUS); break;
            case '-': this.addTokenToList(TokenType.MINUS); break;
            case '*': this.addTokenToList(TokenType.MULT); break;
            case '/': this.addTokenToList(TokenType.DIVISION); break;
            case ' ':
            case '\r':
            case '\t':
                break;
            default:
                System.err.println("Error. Wrong symbol!");
                break;
        }
    }

    private void addTokenToList(TokenType lexeme) {

        String s = this.source.substring(this.posStartOfLexeme, 
                                            this.posCurrentChar);
        this.containedTokens.add(new Token(s, lexeme));
    }

    private boolean isAtEnd() {

        return this.posCurrentChar == this.source.length();
    }
}
