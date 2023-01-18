package barakocoffee.LexicalAnalyzer.Lexer;

import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.InvalidTokenException;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingEndBlockCommentException;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingEndQuotationMarkException;
import barakocoffee.LexicalAnalyzer.Lexer.Exceptions.MissingStartBlockCommentException;

public class Lexer {
    private String[] keywords = {"bago", "new", "boolean", "defecto", "default", "doble", "double",
								"ent", "int", "kar", "char", "eks", "exp", "kar", "char", "kundi",
								"else", "kung", "if", "kuwerdas", "string", "lobo", "float", "para",
								"for", "protektado", "protected", "pribado", "private", "pinal", "final",
								"publiko", "public", "putol", "break", "klase", "class", "pinahaba",
								"extends", "ito", "this", "pasok", "scan", "labas", "print", "tulong",
								"help", "arrayParser"};
	private String[] reservedWords = {"prinsipal", "main", "tuloy", "continue"};
    private String[] operators = {"+","-","*","/","=","%","~","^","++","--","+=","-=","*=","/=","==","%=","~=",
                                "^=","<",">","!","<=",">=","!=","||","&&"};
    private String[] separators = {",","{","}",";"};
    // ADD BOOLEAN_LITERAL

    public Lexeme removeLineComment(String lineOfcode) {
        for (int i = 1; i < lineOfcode.length(); i++) {
            if (lineOfcode.substring(i - 1, i + 1).equals("//")) {
                return new Lexeme(lineOfcode.substring(0, i - 1), false);
            }
        }
        return new Lexeme(lineOfcode, false);
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

    public int nextToken(String code, int index) throws MissingEndQuotationMarkException {
        for (; index < code.length(); index++) {
            // regex for identifiers
            if (code.substring(index, index + 1).matches("[a-zA-Z0-9_]")) { 
                continue;
            }
            // regex for operations with two characters such as +=, ==, <=, ++, ||, etc.
            if (code.substring(index, index + 2 < code.length() ? index + 2 : index + 1).matches("[+-[*]/%~^=<>!]=|[+]{2}|--|[|]{2}|[&]{2}")) {
                return index + 2;
            }
            // regex for operations with one character such as +, -, ~, =, >, etc.
            if (code.substring(index, index + 1).matches("[+-[*]/~^<>=![{][}]]")) { 
                return index +1;
            }
            // add a method that will get the whole String Literal such as "I am a String Literal"
            if (code.charAt(index) == '\"') {
                return nextStringLiteral(code, index + 1);
            }

            return index;
        }
        return 0;
    }

    public int nextStringLiteral(String code, int index) throws MissingEndQuotationMarkException {
        for (; index < code.length(); index++) {
            if (code.charAt(index) == '\"') {
                return index + 1;
            }
        }

        throw new MissingEndQuotationMarkException();
    }

    public Token identifyToken(String token) throws InvalidTokenException {
        if (token.charAt(0) == '\"') {
            return new Token(token, "STRING_LITERAL");
        }
        for (String keyword : keywords) {
            if (token.equals(keyword)) {
                return new Token(token, "KEYWORD");
            }
        }
        for (String reservedWord : reservedWords) {
            if (token.equals(reservedWord)) {
                return new Token(token, "RESERVED_WORD");
            }
        }
        for (String operator : operators) {
            if (token.equals(operator)) {
                return new Token(token, "OPERATOR");
            }
        }
        for (String separator : separators) {
            if (token.equals(separator)) {
                return new Token(token, "SEPARATOR");
            }
        }
        if (token.matches("true|false")) {
            return new Token(token, "BOOLEAN_LITERAL");
        }
        if (token.matches("_*[a-zA-Z_]+[a-zA-Z0-9_]*")) {
            return new Token(token, "IDENTIFIER");
        }
        if (token.matches("[0-9]*")) {
            return new Token(token, "INTEGER_LITERAL");
        }

        throw new InvalidTokenException(token);
    }
}