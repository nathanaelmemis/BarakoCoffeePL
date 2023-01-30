package barakocoffee;

public class Test {
    public static void main(String[] args) {
        int number = 0;

        for (int i = 0; i < 10; i++, number++) {
            if (i == 5) {
                break;
            }
        }

        System.out.println(number);
    }
}