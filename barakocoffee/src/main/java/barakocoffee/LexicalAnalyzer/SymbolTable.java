package barakocoffee.LexicalAnalyzer;

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

    public void printSymbolTable() {
        for (int i = 0; i < symbolTable.size(); i++) {
            System.out.println(symbolTable.get(i).getType() + " : " + symbolTable.get(i).getLexeme());
        }
    }

    public void printLexemes() {
        System.out.print("CODE: ");
        for (int i = 0; i < symbolTable.size(); i++) {
            System.out.print(symbolTable.get(i).getLexeme() + " ");
        }
        System.out.println();
    }
}