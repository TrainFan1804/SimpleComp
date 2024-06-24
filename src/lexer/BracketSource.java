package src.lexer;

// java import
import java.util.Stack;

/**
 * This class save the amount of brackets in the source.
 * 
 * @author                              o.le
 * @version                             0.1
 * @since                               0.45
 */
public class BracketSource {

    Stack<TokenType> leftBracList;
    Stack<TokenType> rightBracList;

    BracketSource() {

        this.leftBracList = new Stack<>();
        this.rightBracList = new Stack<>();
     }

    public void addBracket(Stack<TokenType> stack, TokenType bracketType) {

        stack.push(bracketType);
    }

    public TokenType checkBracketStack(Stack<TokenType> stack) {

        if (!stack.isEmpty()) {

            return stack.pop();
        }

        return null;
    }

}
