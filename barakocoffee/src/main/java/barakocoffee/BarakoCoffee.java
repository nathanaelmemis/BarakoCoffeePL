package barakocoffee;

import java.io.FileNotFoundException;

import barakocoffee.LexicalAnalyzer.LexicalAnalyzer;

public class BarakoCoffee 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        lexicalAnalyzer.scan("src\\main\\java\\barakocoffee\\samplecode.bc");
    }
}
