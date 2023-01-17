package barakocoffee.LexicalAnalyzer.Lexer;

public class Lexeme {
    public String lexeme;
    public Boolean isBlockComment;

    Lexeme(String lexeme, Boolean isBlockComment) {
        this.lexeme = lexeme;
        this.isBlockComment = isBlockComment;
    }
}