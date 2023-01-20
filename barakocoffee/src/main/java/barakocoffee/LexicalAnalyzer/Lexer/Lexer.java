package barakocoffee.LexicalAnalyzer.Lexer;

import java.util.HashMap;

import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.InvalidTokenException;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingEndBlockCommentException;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingEndQuotationMarkException;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingStartBlockCommentException;

public class Lexer {
    // private String[] keywords = {"bago", "new", "boolean", "defecto", "default", "doble", "double",
	// 							"ent", "int", "kar", "char", "eks", "exp", "kar", "char", "kundi",
	// 							"else", "kung", "if", "kuwerdas", "string", "lobo", "float", "para",
	// 							"for", "protektado", "protected", "pribado", "private", "pinal", "final",
	// 							"publiko", "public", "putol", "break", "klase", "class", "pinahaba",
	// 							"extends", "ito", "this", "pasok", "scan", "labas", "print", "tulong",
	// 							"help", "arrayParser"};
	// private String[] reservedWords = {"prinsipal", "main", "tuloy", "continue"};
    // private String[] operators = {"+","-","*","/","=","%","~","^","++","--","+=","-=","*=","/=","==","%=","~=",
    //                             "^=","<",">","!","<=",">=","!=","||","&&"};
    // private String[] separators = {",","{","}",";","[","]","(",")","."};

    private HashMap<String, String> lexemes = new HashMap<>();

    public Lexer() {
        // keywords
        lexemes.put("bago", "BAGO_KEYWORD");
        lexemes.put("new", "NEW_KEYWORD");
        lexemes.put("boolean", "BOOLEAN_KEYWORD");
        lexemes.put("defecto", "DEFECTO_KEYWORD");
        lexemes.put("default", "DEFAULT_KEYWORD");
        lexemes.put("doble", "DOBLE_KEYWORD");
        lexemes.put("double", "DOUBLE_KEYWORD");
        lexemes.put("ent", "ENT_KEYWORD");
        lexemes.put("int", "INT_KEYWORD");
        lexemes.put("kar", "KAR_KEYWORD");
        lexemes.put("char", "CHAR_KEYWORD");
        lexemes.put("eks", "EKS_KEYWORD");
        lexemes.put("exp", "EXP_KEYWORD");
        lexemes.put("kundi", "KUNDI_KEYWORD");
        lexemes.put("else", "ELSE_KEYWORD");
        lexemes.put("KUNG", "KUNG_KEYWORD");
        lexemes.put("if", "IF_KEYWORD");
        lexemes.put("kuwerdas", "KUWERDAS_KEYWORD");
        lexemes.put("string", "STRING_KEYWORD");
        lexemes.put("lobo", "LOBO_KEYWORD");
        lexemes.put("float", "FLOAT_KEYWORD");
        lexemes.put("for", "FOR_KEYWORD");
        lexemes.put("protektado", "PROTEKTADO_KEYWORD");
        lexemes.put("protected", "PROTECTED_KEYWORD");
        lexemes.put("pribado", "PRIBADO_KEYWORD");
        lexemes.put("private", "PRIVATE_KEYWORD");
        lexemes.put("pinal", "PINAL_KEYWORD");
        lexemes.put("final", "FINAL_KEYWORD");
        lexemes.put("publiko", "PUBLIKO_KEYWORD");
        lexemes.put("public", "PUBLIC_KEYWORD");
        lexemes.put("putol", "PUTOL_KEYWORD");
        lexemes.put("break", "BREAK_KEYWORD");
        lexemes.put("klase", "klase_KEYWORD");
        lexemes.put("class", "CLASS_KEYWORD");
        lexemes.put("pinahaba", "PINAHABA_KEYWORD");
        lexemes.put("extends", "EXTENDS_KEYWORD");
        lexemes.put("ito", "ITO_KEYWORD");
        lexemes.put("this", "THIS_KEYWORD");
        lexemes.put("pasok", "PASOK_KEYWORD");
        lexemes.put("scan", "SCAN_KEYWORD");
        lexemes.put("labas", "LABAS_KEYWORD");
        lexemes.put("print", "PRINT_KEYWORD");
        lexemes.put("tulong", "TULONG_KEYWORD");
        lexemes.put("help", "HELP_KEYWORD");
        lexemes.put("struct", "STRUCT_KEYWORD");

        // reserved words
        lexemes.put("prinsipal", "PRINSIPAL_RESERVED_WORD");
        lexemes.put("main", "MAIN_RESERVED_WORD");
        lexemes.put("tuloy", "TULOY_RESERVED_WORD");
        lexemes.put("continue", "CONTINUE_RESERVED_WORD");

        // operators
        lexemes.put("+", "ADDITION_OPERATOR");
        lexemes.put("-", "SUBTRACTION_OPERATOR");
        lexemes.put("*", "MULTIPLICATION_OPERATOR");
        lexemes.put("/", "DIVISION_OPERATOR");
        lexemes.put("%", "MODULO_OPERATOR");
        lexemes.put("~", "INTEGER_DIVISION_OPERATOR");
        lexemes.put("^", "EXPONENT_OPERATOR");
        lexemes.put("=", "ASSIGNMENT_OPERATOR");
        lexemes.put("++", "INCREMENT_OPERATOR");
        lexemes.put("--", "DECREMENT_OPERATOR");
        lexemes.put("+=", "ADDITION_ASSIGNMENT_OPERATOR");
        lexemes.put("-=", "SUBTRACTION_ASSIGNMENT_OPERATOR");
        lexemes.put("*=", "MULTIPLICATION_ASSIGNMENT_OPERATOR");
        lexemes.put("/=", "DIVISION_ASSIGNMENT_OPERATOR");
        lexemes.put("%=", "MODULO_ASSIGNMENT_OPERATOR");
        lexemes.put("~=", "INTEGER_DIVISION_ASSIGNMENT_OPERATOR");
        lexemes.put("^=", "EXPONENT_ASSIGNMENT_OPERATOR");
        lexemes.put("!", "NOT_OPERATOR");
        lexemes.put("==", "EQUAL_OPERATOR");
        lexemes.put("!=", "NOT_EQUAL_OPERATOR");
        lexemes.put(">", "GREATER_OPERATOR");
        lexemes.put("<", "LESS_OPERATOR");
        lexemes.put(">=", "GREATER_EQUAL_OPERATOR");
        lexemes.put("<=", "LESS_EQUAL_OPERATOR");
        lexemes.put("||", "OR_OPERATOR");
        lexemes.put("&&", "AND_OPERATOR");

        // delimiters
        lexemes.put("{", "OPEN_CURLY_DELIMITER");
        lexemes.put("}", "CLOSE_CURLY_DELIMITER");
        lexemes.put("[", "OPEN_SQUARE_DELIMITER");
        lexemes.put("]", "CLOSE_SQUARE_DELIMITER");
        lexemes.put("(", "OPEN_PARENTHESIS_DELIMITER");
        lexemes.put(")", "CLOSE_PARENTHESIS_DELIMITER");
        lexemes.put(",", "COMMA_DELIMITER");
        lexemes.put(";", "SEMICOLON_DELIMITER");
        lexemes.put(".", "DOT_DELIMITER");

        // boolean literals
        lexemes.put("true", "TRUE_BOOLEAN_LITERAL");
        lexemes.put("false", "FALSE_BOOLEAN_LITERAL");
        lexemes.put("tama", "TAMA_BOOLEAN_LITERAL");
        lexemes.put("mali", "MALI_BOOLEAN_LITERAL");
    }

    public Boolean isToken(String lexeme) {
        if (!lexemes.containsKey(lexeme)) {
            return false;
        }

        return true;
    }

    public String getType(String lexeme) {
        return lexemes.get(lexeme);
    }

    public Boolean isIdentifier(String lexeme) {
        if (lexemes.containsKey(lexeme)) {
            return false;
        }

        return true;
    }

    public Boolean isOperator(String lexeme) {
        if (!lexeme.matches("[+-[*]/~^<>=!()[{][}]\\[\\];.]")) {
            return false;
        }

        return true;
    }

    public Boolean isDoubleOperator(String lexeme) {
        if (!lexemes.containsKey(lexeme)) {
            return false;
        }

        return true;
    }

    public LineOfCode removeLineComment(String lineOfcode) {
        for (int i = 1; i < lineOfcode.length(); i++) {
            if (lineOfcode.substring(i - 1, i + 1).equals("//")) {
                return new LineOfCode(lineOfcode.substring(0, i - 1), false);
            }
        }
        return new LineOfCode(lineOfcode, false);
    }

    public String removeBlockComment(String code) throws MissingEndBlockCommentException, MissingStartBlockCommentException {
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

    // public int nextToken(String code, int index) throws MissingEndQuotationMarkException {
    //     // regex for identifiers, keywords, reserved words
    //     if (code.substring(index, index + 1).matches("[a-zA-Z0-9_]")) { 
    //         return nextIndentifier(code, index + 1);
    //     }
    //     // regex for operations with two characters such as +=, ==, <=, ++, ||, etc.
    //     if (code.substring(index, index + 2 < code.length() ? index + 2 : index + 1).matches("[+-[*]/%~^=<>!]=|[+]{2}|--|[|]{2}|[&]{2}")) {
    //         return index + 2;
    //     }
    //     // regex for operators and separators
    //     if (code.substring(index, index + 1).matches("[+-[*]/~^<>=!()[{][}]];.")) { 
    //         return index +1;
    //     }
    //     // add a method that will get the whole String Literal such as "I am a String Literal"
    //     if (code.charAt(index) == '\"') {
    //         return nextStringLiteral(code, index + 1);
    //     }

    //     return index + 1;
    // }

    // public int nextIndentifier(String code, int index) throws MissingEndQuotationMarkException {
    //     for (; index < code.length(); index++) {
    //         if (code.substring(index, index + 1).matches("[^a-zA-Z0-9_]")) {
    //             return index;
    //         }
    //     }

    //     throw new MissingEndQuotationMarkException();
    // }

    // public int nextStringLiteral(String code, int index) throws MissingEndQuotationMarkException {
    //     for (; index < code.length(); index++) {
    //         if (code.charAt(index) == '\"') {
    //             return index + 1;
    //         }
    //     }

    //     throw new MissingEndQuotationMarkException();
    // }

    // public Token identifyToken(String token) throws InvalidTokenException {
    //     if (token.charAt(0) == '\"') {
    //         return new Token(token, "STRING_LITERAL");
    //     }
    //     for (String keyword : keywords) {
    //         if (token.equals(keyword)) {
    //             return new Token(token, "KEYWORD");
    //         }
    //     }
    //     for (String reservedWord : reservedWords) {
    //         if (token.equals(reservedWord)) {
    //             return new Token(token, "RESERVED_WORD");
    //         }
    //     }
    //     for (String operator : operators) {
    //         if (token.equals(operator)) {
    //             return new Token(token, "OPERATOR");
    //         }
    //     }
    //     for (String separator : separators) {
    //         if (token.equals(separator)) {
    //             return new Token(token, "SEPARATOR");
    //         }
    //     }
    //     if (token.matches("true|false")) {
    //         return new Token(token, "BOOLEAN_LITERAL");
    //     }
    //     if (token.matches("_*[a-zA-Z_]+[a-zA-Z0-9_]*")) {
    //         return new Token(token, "IDENTIFIER");
    //     }
    //     if (token.matches("[0-9]*")) {
    //         return new Token(token, "INTEGER_LITERAL");
    //     }

    //     throw new InvalidTokenException(token);
    // }
}