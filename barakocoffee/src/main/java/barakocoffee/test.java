package barakocoffee;

public class Test {
    public static void main(String[] args) {
        Cat cat = new Cat();

        cat.talk("meow!");
    }
}

class Cat {
    public void talk(String messsage) {
        System.out.println(messsage);
    }
}