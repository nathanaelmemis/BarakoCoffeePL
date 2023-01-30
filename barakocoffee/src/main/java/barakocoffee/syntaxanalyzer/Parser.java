package barakocoffee.syntaxanalyzer;

import java.util.ArrayList;

import barakocoffee.SymbolTable;

public class Parser {
    // making of parse tree
    private SymbolTable symbolTable;
    private int index = 0;

    public Parser(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public Node createParseTree(Node node) {
        for (; index < symbolTable.getSymbolTable().size(); index++) {
            if (symbolTable.getSymbolTable().get(index).getLexeme().equals("}")) {
                break;
            }
            node.addChild(new Node(symbolTable.getSymbolTable().get(index), null));
            if (symbolTable.getSymbolTable().get(index).getLexeme().equals("{")) {
                index++;
                node.addChild(createParseTree(new Node(null, new ArrayList<Node>()))); 
                node.addChild(new Node(symbolTable.getSymbolTable().get(index), null));
            }
        }
        return node;
    }
    
    public void printParseTree(Node node) {
        if (node == null) {
            return;
        }
        if (node.getToken() != null) {
            if (!node.getToken().getLexeme().matches("[{}};]")) {
                System.out.print(node.getToken().getDepth() + " " + node.getToken().getType() + " ");
            } else {
                System.out.print(node.getToken().getDepth() + " " + node.getToken().getType() + "\n");
            }
        }
        if (node.getChildren() != null) {
            for (Node child : node.getChildren()) {
                printParseTree(child);
            }
        }
    }
}