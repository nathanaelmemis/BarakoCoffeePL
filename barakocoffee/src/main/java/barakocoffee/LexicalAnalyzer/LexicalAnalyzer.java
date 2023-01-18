package barakocoffee.LexicalAnalyzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import barakocoffee.LexicalAnalyzer.Lexer.Lexeme;
import barakocoffee.LexicalAnalyzer.Lexer.Lexer;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingEndBlockCommentException;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingStartBlockCommentException;

public class LexicalAnalyzer {
    public void scan(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        Lexer lexer = new Lexer();
        Lexeme lexeme;
        SymbolTable symbolTable = new SymbolTable();
        String code = "";

        // Removing of Line Comments and Block Comments
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

        // Tokenization
        for (int index = 0, lastIndex = 0; index < code.length(); lastIndex = index) {
            try {
                index = lexer.nextToken(code, index);
            } catch (Exception e) {
                System.out.println("A quotation mark was not closed!");
                System.exit(1);
            }
            if (code.substring(lastIndex, index + 1).equals(" ")) {
                index++;
                continue;
            }
            if (code.substring(lastIndex, index + 1).equals(";")) {
                index++;
            }
            try {
                symbolTable.add(lexer.identifyToken(code.substring(lastIndex, index)));
            } catch (Exception e) {
                System.out.println(e.getMessage() + " is an Invalid Token!");
                System.exit(1);
            }
        }

        symbolTable.printLexemes();
        symbolTable.printSymbolTable();
    }
}