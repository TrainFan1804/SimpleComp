package src.lexer;

// java import
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;
// custom import
import src.lexer.tokens.TokenAction;
import src.lexer.tokens.Var;
import src.lexer.tokens.LeftSqtBracket;
import src.lexer.tokens.Literal;
import src.lexer.tokens.RightSqtBracket;
import src.lexer.tokens.LeftBracket;
import src.lexer.tokens.RightBracket;
import src.lexer.tokens.Comma;
import src.lexer.tokens.Plus;
import src.lexer.tokens.Minus;
import src.lexer.tokens.Multiplication;
import src.lexer.tokens.Division;
import src.lexer.tokens.Equal;
import src.lexer.tokens.Bang;
import src.lexer.tokens.Greater;
import src.lexer.tokens.Less;

/**
 * The class for the scanner. This class is scanning the given code.
 * <p>
 * This class is designed as a <b>Singleton<b>.
 * 
 * @author                              o.le
 * @version                             1.21
 * @since                               0.4
 */
public class LexerScanner {

    private static LexerScanner staticLexerScanner;

    private Source source;
    private List<Token> containedTokens;
    private Map<Character, TokenAction> tokensActions;

    /**
     * Create an lexer scanner object.
     * 
     * @return                          The scanner object.
     */
    public static LexerScanner createLexerScanner() {

        if (LexerScanner.staticLexerScanner == null) {

            LexerScanner.staticLexerScanner = new LexerScanner();
        }

        return LexerScanner.staticLexerScanner;
    }

    /**
     * Create a new lexer scanner.
     */
    private LexerScanner() {

        this.tokensActions = new HashMap<>();
        this.tokensActions.put('{', new LeftBracket());
        this.tokensActions.put('}', new RightBracket());
        this.tokensActions.put('[', new LeftSqtBracket());
        this.tokensActions.put(']', new RightSqtBracket());
        this.tokensActions.put(',', new Comma());
        this.tokensActions.put('+', new Plus());
        this.tokensActions.put('-', new Minus());
        this.tokensActions.put('*', new Multiplication());
        this.tokensActions.put('/', new Division());
        this.tokensActions.put('=', new Equal());
        this.tokensActions.put('!', new Bang());
        this.tokensActions.put('>', new Greater());
        this.tokensActions.put('<', new Less());
        for (char c = '0'; c <= '9'; c++) {

            this.tokensActions.put(c, new Literal());
        }
        this.tokensActions.put('v', new Var());
    }

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
     * Start the identifing process of the current token in the source.
     */
    private void identifyToken() {

        char currentChar = this.source.getNextChar();

        TokenAction a = this.tokensActions.get(currentChar);

        if (a != null) {

            a.action(this);
        } else {
            
            Lexer.error("Invalid character: " + currentChar);
        }
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
    public boolean checkNextChar(char expectedChar) {

        return this.source.checkForDoubleExpression(expectedChar);
    }

    /**
     * Check if the current character is an literal with multiple character.
     * 
     * @param condition                 The condition that is checked.
     */
    public void checkForMultipleExpression(Predicate<Character> condition) {

        this.source.checkForMultipleExpression(condition);
    }

    public String getCurrentLexeme() {

        return this.source.getLexeme();
    }
}
