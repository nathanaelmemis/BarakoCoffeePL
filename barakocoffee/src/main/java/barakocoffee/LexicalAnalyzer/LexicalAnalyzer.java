package barakocoffee.LexicalAnalyzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import barakocoffee.LexicalAnalyzer.Lexer.LineOfCode;
import barakocoffee.LexicalAnalyzer.Lexer.Token;
import barakocoffee.LexicalAnalyzer.Lexer.Lexer;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingEndBlockCommentException;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingStartBlockCommentException;

public class LexicalAnalyzer {
    public SymbolTable scan(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        Lexer lexer = new Lexer();
        LineOfCode lineOfCode;
        SymbolTable symbolTable = new SymbolTable();
        String code = "";

        // Removing of Line Comments and Block Comments
        while (scanner.hasNextLine()) {
            lineOfCode = lexer.removeLineComment(scanner.nextLine());
            code += lineOfCode.lineOfCode;
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
        // for (int index = 0, lastIndex = 0; index < code.length(); lastIndex = index) {
        //     try {
        //         index = lexer.nextToken(code, index);
        //     } catch (MissingEndQuotationMarkException e) {
        //         System.out.println("A quotation mark was not closed!");
        //         System.exit(1);
        //     }
        //     if (code.substring(lastIndex, index).equals(" ")) {
        //         continue;
        //     }
        //     try {
        //         symbolTable.add(lexer.identifyToken(code.substring(lastIndex, index)));
        //     } catch (InvalidTokenException e) {
        //         System.out.println(e.getMessage() + " is an Invalid Token!");
        //         System.exit(1);
        //     }
        // }

        String lexeme = "";

        for (int index = 0; index < code.length(); index++) {
            lexeme += code.substring(index, index + 1);

            // white spaces
            if (lexeme.matches("[\t\n\f\r ]+")) {
                lexeme = "";
                continue;
            }

            // string literals
            else if (lexeme.matches("\"")) {
                while (!lexeme.matches("\"[a-zA-Z_0-9 ]*\"") && ++index < code.length()) {
                    lexeme += code.substring(index, index + 1);
                }
                symbolTable.add(new Token(lexeme, "STRING_LITERAL"));
                lexeme = "";
            }

            // number literals
            else if (lexeme.matches("[0-9]")) {
                while (lexeme.matches("[0-9]+\\.?[0-9]*") && ++index < code.length()) {
                    lexeme += code.substring(index, index + 1);
                }
                if (lexeme.substring(0, lexeme.length() - 1).matches("[0-9]+\\.[0-9]*")) {
                    symbolTable.add(new Token(lexeme.substring(0, lexeme.length() - 1), "FLOAT_LITERAL"));
                } else {
                    symbolTable.add(new Token(lexeme.substring(0, lexeme.length() - 1), "NUMBER_LITERAL"));
                }
                lexeme = "";
                index--;
            }

            // identifiers, keywords, reserved words
            else if (lexeme.matches("[a-zA-Z_]")) {
                while (lexeme.matches("[a-zA-Z_0-9]+") && ++index < code.length()) {
                    lexeme += code.substring(index, index + 1);
                }
                if (lexer.isIdentifier(lexeme.substring(0, lexeme.length() - 1))) {
                    symbolTable.add(new Token(lexeme.substring(0, lexeme.length() - 1), "IDENTIFIER"));
                } else {
                    symbolTable.add(new Token(lexeme.substring(0, lexeme.length() - 1), lexer.getType(lexeme.substring(0, lexeme.length() - 1))));
                }
                lexeme = "";
                index--;
            }

            // operators
            else if (lexeme.matches("[[+]-[*]/~^<>=!()[{][}]\\[\\];.[|][&]]")) {
                if (index + 2 < code.length()) {
                    lexeme += code.substring(index + 1, index + 2);
                } else {
                    lexeme += 0;
                }
                if (lexeme.matches("[[+]-[*]/~^<>=!]=|[+]{2}|--|[|]{2}|[&]{2}")) {
                    symbolTable.add(new Token(lexeme, lexer.getType(lexeme)));
                    index++;
                } else {
                    if (lexer.isToken(lexeme.substring(0, lexeme.length() - 1))) {
                        symbolTable.add(new Token(lexeme.substring(0, lexeme.length() - 1), lexer.getType(lexeme.substring(0, lexeme.length() - 1))));
                    } else {
                        System.out.println(lexeme.substring(0, lexeme.length() - 1) + " is an invalid token!");
                        System.exit(1);
                    }
                }
                lexeme = "";
            }
        }

        // for (int lastIndex = 0, index = 1; index < code.length(); index++) {
        //     // whitespaces
        //     if (code.substring(lastIndex, index).matches("[\t\n\f\r ]+")) {
        //         lastIndex = index;
        //         continue;
        //     }
        //     // string literals
        //     else if (code.substring(lastIndex, lastIndex + 1).equals("\"")) {
        //         for (; index < code.length(); index++) {
        //             if (code.charAt(index) == '\"') {
        //                 symbolTable.add(new Token(code.substring(lastIndex, index), "STRING_LITERAL"));
        //             }
        //         }
                
        //         System.out.println("A Quotation Mark wasn't closed!");
        //         System.exit(1);
        //     }
        //     // number/float literals
        //     else if (code.substring(lastIndex, index).matches("[0-9]")) {
        //         for (; index < code.length(); index++) {
        //             if (!code.substring(lastIndex, index).matches("[0-9]+.?[0-9]*")) {
        //                 if (code.substring(lastIndex, index).matches("[0-9]+.?[0-9]*")) {
        //                     symbolTable.add(new Token(code.substring(lastIndex, index), "BOOLEAN_LITERAL"));
        //                 } else {
        //                     symbolTable.add(new Token(code.substring(lastIndex, index), "STRING_LITERAL"));
        //                 }
        //             }
        //         }
        //     }
        //     // identifiers, keywords, reserved words, operators, and delimiters
        //     else if (!code.substring(lastIndex, index).matches("[a-zA-Z_0-9]+")) {
        //         index--;
        //         if (lexer.isOperator(code.substring(lastIndex, index)) && index + 1 < code.length()) {
        //             if (lexer.isDoubleOperator(code.substring(lastIndex, index + 1))) {
        //                 symbolTable.add(new Token(code.substring(lastIndex, index + 1), lexer.getType(code.substring(lastIndex, index + 1))));
        //                 index++;
        //             } else {
        //                 symbolTable.add(new Token(code.substring(lastIndex, index), lexer.getType(code.substring(lastIndex, index))));
        //             }
        //         } else {
        //             symbolTable.add(new Token(code.substring(lastIndex, index), lexer.isIdentifier(code.substring(lastIndex, index))));
        //         }
        //         lastIndex = index;
        //     }
        // }

        // symbolTable.add(new Token(code.substring(lastIndex, index - 1), lexer.isIdentifier(code.substring(lastIndex, index - 1))));
        // lastIndex = index;

        return symbolTable;
    }
}