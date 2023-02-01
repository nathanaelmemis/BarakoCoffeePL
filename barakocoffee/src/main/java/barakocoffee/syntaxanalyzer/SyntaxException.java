package barakocoffee.syntaxanalyzer;

public class SyntaxException extends Exception{
    public SyntaxException(String strError) {
        super("\n" + strError);
    }
}