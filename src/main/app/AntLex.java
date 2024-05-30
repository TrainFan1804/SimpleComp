package src.main.app;

// java import
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

// custom import
import src.main.app.scanner.Scanner;
import src.main.app.token.Token;

/**
 * The main class for the lexer.
 * 
 * @author o.le
 * @version 0.34
 * @since 0.1
 */
public class AntLex {

    static boolean hadError = false; // check if there is an error while reading the tokens

    public static void main(String[] args) throws IOException {

        if (args.length == 1) {
            
            runFile(args[0]);
        } else {
            
            runPrompt();
        }
    }

    /**
     * Read the file and check the tokens.
     * 
     * @param path The path of the file.
     * @throws IOException
     */
    private static void runFile(String path) throws IOException {
        
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError)
            System.exit(65);
    }

    /**
     * Listen to the terminal to execute the lexer.
     * 
     * @throws IOException
     */
    private static void runPrompt() throws IOException {
        
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null)
                break;
            run(line);
            hadError = false;
        }
    }

    /**
     * This method check the source the lexer is currently scanning.
     * 
     * @param source The source the lexer is checking.
     */
    private static void run(String source) {
       
        Scanner s = new Scanner(source);
        List<Token> tokens = s.sourceScan();

        for (Token t : tokens) {
        
            System.out.println(t);
        }
    }

    /**
     * When an error is happening.
     * 
     * @param errorLine The line where the error happend.
     * @param message The error message.
     * @see AntLex#report
     */
    public static void error(int errorLine, String message) {
        
        report(errorLine, "", message);
    }

    /**
     * Print the report from the error message.
     * 
     * @param where The line where the error happend.
     * @param message The error message.
     */
    private static void report(int errorLine,String where, String message) {

        System.err.println("[line " + errorLine + "] Error" + where + ": " + message);
        hadError = true;
    }
}
