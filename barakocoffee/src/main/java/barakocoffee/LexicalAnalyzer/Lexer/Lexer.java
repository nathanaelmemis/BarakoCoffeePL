package barakocoffee.LexicalAnalyzer.Lexer;

public class Lexer {
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
}