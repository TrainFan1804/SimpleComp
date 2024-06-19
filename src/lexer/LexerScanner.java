package src.lexer;

// java import
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
// custom import
import src.lexer.tokens.TokenAction;
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

/**
 * The class for the scanner. This class is scanning the given code.
 * 
 * @author                              o.le
 * @version                             1.10
 * @since                               0.4
 */
public class LexerScanner {

    private Source source;

    private List<Token> containedTokens;
    private Map<Character, TokenAction> tokensActions;

    /**
     * Create a new simple scanner.
     * 
     * @param source                    The source the scanner is
     *                                  scanning.
     */
    public LexerScanner(Source source) {

        this.source = source;
        this.containedTokens = new LinkedList<>();

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
        for (char c = '0'; c <= '9'; c++) {

            this.tokensActions.put(c, new Literal());
        }
    }

    /**
     * The simple scanner is scanning through his saved source.
     * 
     * @return                          The list of all {@link Token}
     *                                  that was in the source.
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
     * Identify a token in the source.
     */
    private void identifyToken() {

        char currentChar = this.source.getNextChar();

        TokenAction a = this.tokensActions.get(currentChar);

        a.action(this);

        // switch (currentChar) {
            
        //     case '!': 
        //         this.addTokenToList(this.checkForDoubleExpression('=')
        //                                 ? TokenType.BANG_EQUAL
        //                                 : TokenType.BANG);
        //         break;
        //     case '>':
        //         this.addTokenToList(this.checkForDoubleExpression('=')
        //                                 ? TokenType.GREATER_EQUAL 
        //                                 : TokenType.GREATER);
        //         break;
        //     case '<':
        //         this.addTokenToList(this.checkForDoubleExpression('=')
        //                                 ? TokenType.LESS_EQUAL 
        //                                 : TokenType.LESS);
        //         break;
        //     case '0':
        //     case '1':
        //     case '2':
        //     case '3':
        //     case '4':
        //     case '5':
        //     case '6':
        //     case '7':
        //     case '8':
        //     case '9':
        //         this.checkForMultipleExpression();
        //         this.addTokenToList(TokenType.LITERAL);
        //         break;
        //     // ignore all different types of whitespaces
        //     case ' ':
        //     case '\r':
        //     case '\t':
        //         break;
        //     default:
                
        //         SimpleLexer.error("Invalid character: " + currentChar);
        //         break;
        // }
    }

    /**
     * Add a token to the list that save all {@link Token}.
     * 
     * @param lexeme                    The lexeme of the new token.
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
    public boolean checkForDoubleExpression(char expectedChar) {

        return this.source.checkForDoubleExpression(expectedChar);
    }

    /**
     * When an integer value appears the lexer is checking if the
     * following character are also integer. 
     * <p>
     * When the following characters ARE integer the 
     * {@link SimpleScanner#posCurrentChar} is updating.
     */
    public void checkForMultipleExpression() {

        this.source.checkForMultipleExpression();
    }
}
