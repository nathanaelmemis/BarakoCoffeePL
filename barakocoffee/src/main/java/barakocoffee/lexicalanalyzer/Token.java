package barakocoffee.lexicalanalyzer;

public class Token {
    private String lexeme;
    private String type;
    private int depth;
    private int lineNumber;

    public Token (String lexeme, String type, int depth, int lineNumber) {
        this.lexeme = lexeme;
        this.type = type;
        this.depth = depth;
        this.lineNumber = lineNumber;
    }

    public String getLexeme() {
        return lexeme;
    }

    public String getType() {
        return type;
    }

    public int getDepth() {
        return depth;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setType(String type) {
        this.type = type;
    }
}