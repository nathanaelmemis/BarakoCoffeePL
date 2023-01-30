package barakocoffee.lexicalanalyzer;

public class Token {
    private String lexeme;
    private String type;
    private int lineNumber;

    public Token (String lexeme, String type, int lineNumber) {
        this.lexeme = lexeme;
        this.type = type;
        this.lineNumber = lineNumber;
    }

    public String getLexeme() {
        return lexeme;
    }

    public String getType() {
        return type;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setType(String type) {
        this.type = type;
    }
}