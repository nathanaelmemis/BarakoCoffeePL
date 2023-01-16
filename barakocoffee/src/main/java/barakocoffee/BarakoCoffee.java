package barakocoffee;

import java.io.FileNotFoundException;

public class BarakoCoffee 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        lexicalAnalyzer.scan("samplecode.bc");
    }
}
