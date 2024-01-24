package src.main.app.scanner;

// java import
import java.util.ArrayList;
import java.util.List;

// custom import
import src.main.app.AntLex;
import src.main.app.token.Token;
import src.main.app.token.TokenType;

/**
 * The class the token scanner
 * 
 * @author o.le
 * @version 0.23
 * @since 0.1
 */
public class Scanner {

    private String source;
    private List<Token> tokens = new ArrayList<>();

    private int posStartOfLexeme; // start position of the current lexeme
    private int posCurrentChar; // position of current char

    /**
     * Construct an token scanner
     *  
     * @param source The source the scanner is looking through
     */
    public Scanner(String source) {
        this.source = source;
    }

    /**
     * Scan the source for tokens
     * 
     * @return All valid tokens in the current line
     */
    public List<Token> sourceScan() {

        while (!this.isAtEnd()) {
            
            this.posStartOfLexeme = this.posCurrentChar;
            this.scanToken();
        }

        this.tokens.add(new Token(TokenType.EOF, "", null));
        return this.tokens;
    }

    /**
     * Check if the current token is valid
     */
    private void scanToken() {
        
        char currentChar = source.charAt(this.posCurrentChar++); // each time the pointer of the current char is moving one forward

        switch (currentChar) {

            case '.': addToken(TokenType.DOT); break;
            case '-': addToken(TokenType.MINUS); break;
            case '+': addToken(TokenType.PLUS); break;
            case ';': addToken(TokenType.SEMICOLON); break;
            case '*': addToken(TokenType.STAR); break;
            case '/': addToken(TokenType.SLASH); break;
            // ignore whitespaces, ' ', '\r' and '\t' are for different OS
            case ' ':
            case '\r':
            case '\t': break;
            default:
                AntLex.error("Unexpected character.");;
                break;
        }

    }

    /**
     * Add the current token to the token list
     * 
     * @param type The type of the token
     */
    private void addToken(TokenType type) {
        this.addToken(type, null);
    }

    /**
     * Add the current token to the tokenlist
     * 
     * @param type The type of the token
     * @param literal The literal object of the token
     */
    private void addToken(TokenType type, Object literal) {
        
        String text = this.source.substring(this.posStartOfLexeme, posCurrentChar);
        this.tokens.add(new Token(type, text, literal));
    }

    /**
     * Check if the source pointer is at the end of the file/prompt
     */
    private boolean isAtEnd() {
        return this.posCurrentChar >= this.source.length(); 
    }

}
