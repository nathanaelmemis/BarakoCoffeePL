package barakocoffee.LexicalAnalyzer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import barakocoffee.LexicalAnalyzer.Lexer.Token;

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
        for (int i = 0; i < symbolTable.size(); i++) {
            System.out.println(symbolTable.get(i).getType() + " : " + symbolTable.get(i).getLexeme());
        }
    }

    public void printSymbolTable(String file, Boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(file, append);
        for (int i = 0; i < symbolTable.size(); i++) {
            fileWriter.write(String.format("%-" + 30 + "s" ,symbolTable.get(i).getType()) + " : " + symbolTable.get(i).getLexeme() + "\n");
        }
        fileWriter.close();
    }
}