package barakocoffee.LexicalAnalyzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import barakocoffee.LexicalAnalyzer.Lexer.Lexeme;
import barakocoffee.LexicalAnalyzer.Lexer.Lexer;
import barakocoffee.LexicalAnalyzer.Lexer.MissingEndBlockCommentException;
import barakocoffee.LexicalAnalyzer.Lexer.MissingStartBlockCommentException;
import barakocoffee.LexicalAnalyzer.Tokenizer.SymbolTable;
import barakocoffee.LexicalAnalyzer.Tokenizer.Tokenizer;

public class LexicalAnalyzer {
    public void scan(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        Lexer lexer = new Lexer();
        Lexeme lexeme;
        Tokenizer tokenizer = new Tokenizer();
        SymbolTable symbolTable = new SymbolTable();
        String code = "";

        // Lexer
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

        // Tokenizer
        for (int index = 0, lastIndex = 0; index < code.length(); lastIndex = index) {
            try {
                index = tokenizer.nextToken(code, index);
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
                symbolTable.add(tokenizer.identifyToken(code.substring(lastIndex, index)));
            } catch (Exception e) {
                System.out.println(e.getMessage() + " is an Invalid Token!");
                System.exit(1);
            }
        }

        symbolTable.printCode();
        symbolTable.printSymbolTable();
    }
}