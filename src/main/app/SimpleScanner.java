package src.main.app;

import java.util.LinkedList;
import java.util.List;

/**
 * The class for the scanner. This class is scanning the given code.
 * 
 * @author                              o.le
 * @version                             0.2
 * @since                               0.4
 */
public class SimpleScanner {

    private String source;
    private int posStartOfToken;
    private int posCurrentInToken;

    private List<Token> containedTokens;

    public SimpleScanner(String s) {

        this.source = s;
        this.containedTokens = new LinkedList<>();
    }

    public List<Token> scanSource() {

        while(!this.isAtEnd()) {

            this.posStartOfToken = this.posCurrentInToken;
            this.identifyToken();
        }

        this.containedTokens.add(new Token("", TokenType.EOF));
        return this.containedTokens;
    }

    private void identifyToken() {

        char currentChar = this.source.charAt(posCurrentInToken++);

        switch (currentChar) {
            case '+':
                this.addTokenToList(TokenType.PLUS);
                break;
            case '-':
                this.addTokenToList(TokenType.MINUS);
                break;
            case '*':
                this.addTokenToList(TokenType.MULTIPLICATION);
                break;
            case '/':
                this.addTokenToList(TokenType.DIVISION);
                break;
            case 'e':
                this.addTokenToList(TokenType.PRINT);
                break;
            default:
                System.err.println("Error. Wrong symbol!");
                break;
        }
    }

    private void addTokenToList(TokenType lexeme) {

        String s = this.source.substring(this.posStartOfToken, 
                                            this.posCurrentInToken);
        this.containedTokens.add(new Token(s, lexeme));
    }

    private boolean isAtEnd() {

        return this.posCurrentInToken == this.source.length();
    }
}
