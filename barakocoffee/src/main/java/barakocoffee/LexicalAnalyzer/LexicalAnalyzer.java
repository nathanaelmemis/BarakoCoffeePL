package barakocoffee.LexicalAnalyzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import barakocoffee.LexicalAnalyzer.Lexer.Lexeme;
import barakocoffee.LexicalAnalyzer.Lexer.Lexer;
import barakocoffee.LexicalAnalyzer.Lexer.MissingEndBlockCommentException;
import barakocoffee.LexicalAnalyzer.Lexer.MissingStartBlockCommentException;

public class LexicalAnalyzer {
    public String scan(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        Lexer lexer = new Lexer();
        Lexeme lexeme;
        String code = "";

        while (scanner.hasNextLine()) {
            lexeme = lexer.removeLineComment(scanner.nextLine());
            code += lexeme.lexeme;
        }

        try {
            code = lexer.removeBlockComment(code);
        } catch (MissingEndBlockCommentException e) {
            System.out.println("A block comment was not closed!");
            System.exit(1);
        } catch (MissingStartBlockCommentException e) {
            System.out.println("A start of a block comment is missing!");
            System.exit(1);
        }

        return code;
    }
}