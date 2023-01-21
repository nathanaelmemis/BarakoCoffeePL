package barakocoffee.lexicalanalyzer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SymbolTable {
    private ArrayList<Token> symbolTable = new ArrayList<Token>();

    public void add(Token token) {
        symbolTable.add(token);
    }

    public ArrayList<Token> getSymbolTable() {
        return symbolTable;
    }

    public void printLexemes() {
        System.out.print("CODE: ");
        for (int i = 0; i < symbolTable.size(); i++) {
            System.out.print(symbolTable.get(i).getLexeme() + " ");
        }
        System.out.println();
    }

    public void printLexemes(String file, Boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(file, append);
        fileWriter.write("CODE: ");
        for (int i = 0; i < symbolTable.size(); i++) {
            fileWriter.write(symbolTable.get(i).getLexeme() + " ");
        }
        fileWriter.write("\n");
        fileWriter.close();
    }

    public void printSymbolTable() {
        int spaces = longestLexemeLength() + 5;
        System.out.println(String.format("%-" + spaces + "s", "LEXEMES") + "TOKENS\n\n");
        for (int i = 0; i < symbolTable.size(); i++) {
            System.out.println(String.format("%-" + spaces + "s", symbolTable.get(i).getLexeme()) + symbolTable.get(i).getType() + "\n");
        }
    }

    public void printSymbolTable(String file, Boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(file, append);
        int spaces = longestLexemeLength() + 5;
        fileWriter.write(String.format("%-" + spaces + "s", "LEXEMES") + "TOKENS\n\n");
        for (int i = 0; i < symbolTable.size(); i++) {
            fileWriter.write(String.format("%-" + spaces + "s", symbolTable.get(i).getLexeme()) + symbolTable.get(i).getType() + "\n");
        }
        fileWriter.close();
    }

    public int longestLexemeLength() {
        int longestLexemeLength = 12;
        for (int i = 0; i < symbolTable.size(); i++) {
            if (symbolTable.get(i).getLexeme().length() > longestLexemeLength) {
                longestLexemeLength = symbolTable.get(i).getLexeme().length();
            }
        }

        return longestLexemeLength;
    }
}