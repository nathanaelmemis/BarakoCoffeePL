package barakocoffee.lexicalanalyzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import barakocoffee.lexicalanalyzer.exceptions.MissingEndBlockCommentException;
import barakocoffee.lexicalanalyzer.exceptions.MissingStartBlockCommentException;

public class Lexer {
    public SymbolTable scan(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        TokenHashMap tokenHashMap = new TokenHashMap();
        SymbolTable symbolTable = new SymbolTable();
        String lineOfCode = "";
        String code = "";
        String lexeme = "";

        // removing of line comments
        while (scanner.hasNextLine()) {
            lineOfCode = removeLineComment(scanner.nextLine());
            code += lineOfCode + "\n";
        }
        // removing of block comments
        try {
            code = removeBlockComment(code);
        } catch (MissingEndBlockCommentException e) {
            System.out.println("Error: A block comment was not closed");
            System.exit(1);
        } catch (MissingStartBlockCommentException e) {
            System.out.println("Error: A start of a block comment is missing");
            System.exit(1);
        }

        // Tokenization
        code += " ";
        for (int index = 0; index < code.length(); index++) {
            lexeme += code.substring(index, index + 1);

            // white spaces
            if (lexeme.matches("[\t\n\f\r ]+")) {
                lexeme = "";
                continue;
            }

            // char literals
            else if (lexeme.matches("\'")) {
                if (++index < code.length()) {
                    lexeme += code.substring(index, index + 1);
                } else {
                    System.out.println ("Error: Invalid character literal: " + lexeme);
                    System.exit(1);
                }
                if (++index < code.length()) {
                    lexeme += code.substring(index, index + 1);
                } else {
                    System.out.println ("Error: Invalid character literal: " + lexeme);
                    System.exit(1);
                }
                if (!lexeme.matches("\'.?\'")) {
                    System.out.println ("Error: Invalid character literal: " + lexeme);
                    System.exit(1);
                }
                symbolTable.add(new Token(lexeme, "CHARACTER_LITERAL"));
                lexeme = "";
            }

            // string literals
            else if (lexeme.matches("\"")) {
                while (!lexeme.matches("\".*\"") && ++index < code.length()) {
                    if (code.substring(index, index + 1).matches("\n")) {
                        System.out.println ("Error: String literal is not properly closed: " + lexeme);
                        System.exit(1);
                    }
                    lexeme += code.substring(index, index + 1);
                }
                if (!lexeme.matches("\".*\"")) {
                    System.out.println ("Error: String literal is not properly closed: " + lexeme);
                    System.exit(1);
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
                if (tokenHashMap.isToken(lexeme.substring(0, lexeme.length() - 1))) {
                    symbolTable.add(new Token(lexeme.substring(0, lexeme.length() - 1), tokenHashMap.getType(lexeme.substring(0, lexeme.length() - 1))));
                } else {
                    symbolTable.add(new Token(lexeme.substring(0, lexeme.length() - 1), "IDENTIFIER"));
                }
                lexeme = "";
                index--;
            }

            // operators
            else if (lexeme.matches("[[+]-[*]/~^<>=!()[{][}]\\[\\];.[|][&]]")) {
                if (index + 1 < code.length()) {
                    lexeme += code.substring(index + 1, index + 2);
                } else {
                    lexeme += 0;
                }
                if (lexeme.matches("[[+]-[*]/~^<>=!]=|[+]{2}|--|[|]{2}|[&]{2}")) {
                    symbolTable.add(new Token(lexeme, tokenHashMap.getType(lexeme)));
                    index++;
                } else {
                    if (tokenHashMap.isToken(lexeme.substring(0, lexeme.length() - 1))) {
                        symbolTable.add(new Token(lexeme.substring(0, lexeme.length() - 1), tokenHashMap.getType(lexeme.substring(0, lexeme.length() - 1))));
                    } else {
                        System.out.println("Error: Invalid token: " + lexeme.substring(0, lexeme.length() - 1));
                        System.exit(1);
                    }
                }
                lexeme = "";
            } 
            
            // invalid token
            else {
                System.out.println("Error: Invalid token: " + lexeme);
                System.exit(1);
            }
            
        }

        // check if a reserved word was used as identifier
        for (int i = 1; i < symbolTable.getSymbolTable().size(); i++) {
            String symbol = symbolTable.getSymbolTable().get(i - 1).getType();
            if (symbol.equals("ENT_KEYWORD")
                || symbol.equals("INT_KEYWORD")
                || symbol.equals("KAR_KEYWORD")
                || symbol.equals("CHAR_KEYWORD")
                || symbol.equals("BOOLEAN_KEYWORD")
                || symbol.equals("STRING_KEYWORD")
                || symbol.equals("LOBO_KEYWORD")
                || symbol.equals("FLOAT_KEYWORD")
                || symbol.equals("KLASE_KEYWORD")
                || symbol.equals("CLASS_KEYWORD")
                || symbol.equals("STRUCT_KEYWORD")) {
                if (symbolTable.getSymbolTable().get(i).getType().matches(".*_KEYWORD")) {
                    System.out.println("Error: Invalid Identifier Is Reserved Word: " + symbolTable.getSymbolTable().get(i).getLexeme());
                    System.exit(1);
                }
            }
        }

        return symbolTable;
    }

    private String removeLineComment(String lineOfCode) {
        for (int i = 1; i < lineOfCode.length(); i++) {
            if (lineOfCode.substring(i - 1, i + 1).equals("//")) {
                return lineOfCode.substring(0, i - 1);
            }
        }
        return lineOfCode;
    }

    private String removeBlockComment(String code) throws MissingEndBlockCommentException, MissingStartBlockCommentException {
        for (int i = 1; i < code.length(); i++) {
            if (code.substring(i - 1, i + 1).equals("/*")) {
                code = code.substring(0, i -1) + findEndBlockComment(code.substring(i + 1));
                i--;
            } else if (code.substring(i - 1, i + 1).equals("*/")) {
                throw new MissingStartBlockCommentException();
            }
        }

        return code;
    }

    private String findEndBlockComment(String substring) throws MissingEndBlockCommentException {
        for (int i = 1; i < substring.length(); i++) {
            if (substring.substring(i - 1, i + 1).equals("*/")) {
                return substring.substring(i + 1);
            }
        }

        throw new MissingEndBlockCommentException();
    }
}