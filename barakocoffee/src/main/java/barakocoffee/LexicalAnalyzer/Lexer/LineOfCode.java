package barakocoffee.LexicalAnalyzer.Lexer;

public class LineOfCode {
    public String lineOfCode;
    public Boolean isBlockComment;

    LineOfCode(String lineOfCode, Boolean isBlockComment) {
        this.lineOfCode = lineOfCode;
        this.isBlockComment = isBlockComment;
    }
}