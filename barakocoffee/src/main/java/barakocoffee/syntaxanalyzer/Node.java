package barakocoffee.syntaxanalyzer;

import java.util.ArrayList;
import barakocoffee.Token;

public class Node {
    private Token token;
    private ArrayList<Node> children;

    public Node(Token token, ArrayList<Node> children) {
        this.token = token;
        this.children = children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public Token getToken(){
        return token;
    }
    
    public ArrayList<Node> getChildren() {
        return children;
    }
}