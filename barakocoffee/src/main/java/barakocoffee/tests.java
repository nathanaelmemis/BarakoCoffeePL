package barakocoffee;

public class tests {
    public static void main(String[] args) {
        System.out.println("&".matches("[+-[*]/%~^=<>!]=|[+]{2}|--|[|]{2}|[&]{2}"));
    }

    // regex for identifiers
    // System.out.println("a0".matches("_*[a-zA-Z_]+[a-zA-Z0-9_]*"));
}
