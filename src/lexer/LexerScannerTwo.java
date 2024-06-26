package src.lexer;

// java import
import java.util.List;
import java.util.LinkedList;

/**
 * The main class for the lexer. This is the class that run the lexer.
 * <p>
 * This class is designed as a <b>Singleton<b>.
 * 
 * @author                              o.le
 * @version                             1.25
 * @since                               0.1
 */
public class LexerScannerTwo {

    private static LexerScannerTwo staticLexerScanner;

    private Source source;
    private List<Token> containedTokens;

    /**
     * Create an lexer scanner object.
     * 
     * @return                          The scanner object.
     */
    public static LexerScannerTwo createLexerScanner() {

        if (LexerScannerTwo.staticLexerScanner == null) {

            LexerScannerTwo.staticLexerScanner = new LexerScannerTwo();
        }

        return LexerScannerTwo.staticLexerScanner;
    }

    private LexerScannerTwo() { }

    /**
     * Reset the scanner with a new source to scan.
     * 
     * @param source                    The new source to scan.
     */
    public void initScanner(Source source) {

        this.source = source;
        this.containedTokens = new LinkedList<>();
    }

    /**
     * The scanner start his scan through the saved source.
     * 
     * @return                          All {@link Token} that was in
     *                                  the source.
     */
    public List<Token> scanSource() {

        while(!this.source.isAtEnd()) {

            this.source.startLexeme();
            this.identifyToken();
        }

        this.containedTokens.add(new Token("", TokenType.EOF));
        return this.containedTokens;
    }

    /**
     * Add a token to the token list from the source.
     * 
     * @param lexeme                    The lexeme / type of the new 
     *                                  token.
     */
    public void addTokenToList(TokenType lexeme) {

        String s = this.source.getLexeme();
        this.containedTokens.add(new Token(s, lexeme));
    }

    /**
     * Start the identifing process of the current token in the source.
     */
    private void identifyToken() {

        char currentChar = this.source.getNextChar();

        switch(currentChar) {

            // easy scan
            case '{': this.addTokenToList(TokenType.LEFT_BRAC); break;
            case '}': this.addTokenToList(TokenType.RIGHT_BRAC); break;
            case '[': this.addTokenToList(TokenType.LEFT_SQBRA); break;
            case ']': this.addTokenToList(TokenType.RIGHT_SQBRA); break;
            case '+': this.addTokenToList(TokenType.PLUS); break;
            case '-': this.addTokenToList(TokenType.MINUS); break;
            case '*': this.addTokenToList(TokenType.MULT); break;
            case '/': this.addTokenToList(TokenType.DIVISION); break;
            case ',': this.addTokenToList(TokenType.COMMA); break;
            // ignore whitspace
            case ' ': case '\t':
            case '\f': case '\r': break;

            // medium scan
            case '=': 
                switch(this.source.getNextChar()){ 

                    case '=': this.addTokenToList(TokenType.EQUAL_EQUAL); break;
                    default:
                        this.source.setCharBack();
                        this.addTokenToList(TokenType.EQUAL);
                }
                break;
            case '!':
                switch(this.source.getNextChar()) {
                    
                    case '=': this.addTokenToList(TokenType.BANG_EQUAL);
                    default:
                        this.source.setCharBack();
                        this.addTokenToList(TokenType.BANG);
                }
                break;
            case '<': 
                switch(this.source.getNextChar()) {
                        
                    case '=': this.addTokenToList(TokenType.LESS_EQUAL);
                    default:
                        this.source.setCharBack();
                        this.addTokenToList(TokenType.LESS);
                }
                break;
            case '>': this.addTokenToList(TokenType.GREATER);
                switch(this.source.getNextChar()) {
                            
                    case '=': this.addTokenToList(TokenType.LESS_EQUAL);
                    default:
                        this.source.setCharBack();
                        this.addTokenToList(TokenType.LESS);
                }
                break;
            
            // hard scan
            case '0': case '1':
            case '2': case '3':
            case '4': case '5':
            case '6': case '7':
            case '8': case '9':
                this.source.checkForMultipleExpression(c -> c >= '0' && c <= '9');
                this.addTokenToList(TokenType.LITERAL);
                break;
            case 'a': case 'A':
            case 'b': case 'B':
            case 'c': case 'C':
            case 'd': case 'D':
            case 'e': case 'E':
            case 'f': case 'F':
            case 'g': case 'G':
            case 'h': case 'H':
            case 'i': case 'I':
            case 'j': case 'J':
            case 'k': case 'K':
            case 'l': case 'L':
            case 'm': case 'M':
            case 'n': case 'N':
            case 'o': case 'O':
            case 'p': case 'P':
            case 'q': case 'Q':
            case 'r': case 'R':
            case 's': case 'S':
            case 't': case 'T':
            case 'u': case 'U':
            case 'v': case 'V':
            case 'w': case 'W':
            case 'x': case 'X':
            case 'y': case 'Y':
            case 'z': case 'Z':
                this.checkForIdentifierOrKeyword();
                break;
            default:
                Lexer.error("Invalid character: " + currentChar);
        }
    }

    private void checkForIdentifierOrKeyword() {

        this.source.saveCurrentChar();
        // very bad
        switch (this.source.getCurrentChar()) {
            case 'v':
                // check for "var"
                if (this.source.getNextChar() == 'a') {

                    if (this.source.getNextChar() == 'r') {

                        this.addTokenToList(TokenType.VAR);
                        return;
                    }
                }
            case 'p':
                // check for "print"
                if (this.source.getNextChar() == 'r') {

                    if (this.source.getNextChar() == 'i') {

                        if (this.source.getNextChar() == 'n') {

                            if (this.source.getNextChar() == 't') {

                                this.addTokenToList(TokenType.PRINT);
                                return;
                            }
                        }
                    }
                }
            case 'i':
                // check for "if"
                if (this.source.getNextChar() == 'f') {

                    this.addTokenToList(TokenType.IF);
                    return;
                }
            case 'e':
                // check for "else"
                if (this.source.getNextChar() == 'l') {

                    if (this.source.getNextChar() == 's') {

                        if (this.source.getNextChar() == 'e') {

                            this.addTokenToList(TokenType.ELSE);
                            return;
                        }
                    }
                }
            case 'd':
                // check for "def"
                if (this.source.getNextChar() == 'e') {

                    if (this.source.getNextChar() == 'f') {

                        this.addTokenToList(TokenType.FUNCTION);
                        return;
                    }
                }
            case 'r':
                // check for "return"
                if (this.source.getNextChar() == 'e') {

                    if (this.source.getNextChar() == 't') {

                        if (this.source.getNextChar() == 'u') {

                            if (this.source.getNextChar() == 'n') {

                                this.addTokenToList(TokenType.RETURN);
                                return;
                            }
                        }
                    }
                }
            // default:
            //     // non keyword are not recognize yet
            //     this.addTokenToList(TokenType.IDENTIFIER);
            //     break;
            
            // here reset the pointer to the start position of the lexeme 
            // because the current lexeme is no keyword
            this.source.resetCurrentChar();
        }

        this.source.checkForMultipleExpression(c -> c >= 'A' && c <= 'Z' 
                                                || c >= 'a' && c <= 'z');
        this.addTokenToList(TokenType.IDENTIFIER);
    }
}
