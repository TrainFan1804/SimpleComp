package src.main.app.scanner;

// java import
import java.util.List;
import java.util.ArrayList;
// custom import
import src.main.app.AntLex;
import src.main.app.token.Token;
import src.main.app.token.TokenType;

/**
 * The class of the token scanner.
 * 
 * @author o.le
 * @version 0.3
 * @since 0.1
 */
public class Scanner {

    private String source;
    private List<Token> tokens = new ArrayList<>();

    private int posStartOfLexeme; // start position of the current lexeme
    private int posCurrentChar; // position of current char
    private int currentLine;

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

        this.tokens.add(new Token(TokenType.EOF, "", null, this.currentLine));
        return this.tokens;
    }

    /**
     * Check if the current token is valid
     */
    private void scanToken() {
        
        char currentChar = returnNext(); // each time the pointer of the current char is moving one forward

        switch (currentChar) {

            case '(': addToken(TokenType.LEFT_PAREN); break;
            case ')': addToken(TokenType.RIGHT_PAREN); break;
            case '{': addToken(TokenType.LEFT_BRACE); break;
            case '}': addToken(TokenType.RIGHT_BRACE); break;
            case ',': addToken(TokenType.COMMA); break;
            case '.': addToken(TokenType.DOT); break;
            case '-': addToken(TokenType.MINUS); break;
            case '+': addToken(TokenType.PLUS); break;
            case ';': addToken(TokenType.SEMICOLON); break;
            case '*': addToken(TokenType.STAR); break;
            case '/': addToken(TokenType.SLASH); break;
            case '!':
                addToken(isDoubleEx('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
                break;
            case '=':
                addToken(isDoubleEx('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
                break;
            case '<':
                addToken(isDoubleEx('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
                break;
            case '>':
                addToken(isDoubleEx('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
            // ignore whitespaces, ' ', '\r' and '\t' are for different OS
            case ' ':
            case '\r':
            case '\t': break;
            // The '#' is a comment so the lexer need to ignore this symbole. Also need to ignore the rest of the line
            case '#': 
                // check every char after the '#' till there is a '\n'
                while (isComment() != '\n' && !isAtEnd()) returnNext(); 
                break;
            default:
                AntLex.error(this.currentLine,"Unexpected character.");;
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
        this.tokens.add(new Token(type, text, literal, this.currentLine));
    }

    /**
     * Check if the current char is a new line
     * 
     * @return The current char
     */
    private char isComment() {

        if (isAtEnd()) return '\0';
        return this.source.charAt(posCurrentChar);
    }
    /**
     * Check if there is a double expression like '!=' or '>='
     * @param possible
     * @return
     */
    private boolean isDoubleEx(char possible) {

        if (this.isAtEnd()) return false;
        if (this.source.charAt(this.posCurrentChar) != possible) return false;

        this.posCurrentChar++;
        return true;
    }

    /**
     * Return the char after the pointer
     * 
     * @return The char
     */
    private char returnNext() {
        
        return this.source.charAt(posCurrentChar++);
    }
    /**
     * Check if the source pointer is at the end of the file/prompt
     * 
     * @return If the pointer reached the last char
     */
    private boolean isAtEnd() {
        
        return this.posCurrentChar >= this.source.length(); 
    }

}
