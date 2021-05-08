import java.util.*;

class Human {
    private String name = "";
    private int age = 0;
    private double ht = 0;

    public Human() {
        // default constructor
        System.out.println("Inside constructor");
        this.name = "a";
        this.age = 10;
        this.ht = 5.7;
    }

    public Human(String name, int age, double ht) {
        // parameterised constructor
        System.out.println("Inside parameterised constructor");
        this.name = name;
        this.age = age;
        this.ht = ht;
    }

    public void display() {
        System.out.println(this.name + " " + this.age + " " + this.ht);
    }

    public void changeDetails(String n, int a, double h) {
        name = n;
        age = a;
        ht = h;
    }

    private static int counting = 20;

    public void displayCount() {
        System.out.println(counting);
    }

    public static void changeCount(int num) {
        counting = num;
    }
}

public class demo {

    public int specialNumber = 10;

    public static int count = 25;

    public static void implementation() {
        Human h1 = new Human("ABC", 16, 6.2);
        // st.name = "ABC";
        // h1.display();
        // h1.changeDetails("abc", 15, 6.5);
        // h1.display();
        h1.displayCount();
        h1.changeCount(25);
        h1.displayCount();

        Human h2 = new Human();
        h2.displayCount();
    }

    public static class Cars {
        public String name;
        public int price;

        public Cars(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    public void func2() {
        System.out.println("Hello in func2");
        System.out.println(count);
    }

    public void func() {
        System.out.println("I'm inside non-static function");
        System.out.println(this.specialNumber);
        func2();
    }

    public static void dummyFunc() {
        // func2();
        demo d1 = new demo();

        d1.func();
        d1.specialNumber = 100;
        d1.count = 36;
        d1.func();

        demo d2 = new demo();
        d2.func();
    }

    public static void main(String[] args) {
        implementation();

        // Cars c1 = new Cars("ABC", 1000000);

        // System.out.println(c1.name + " " + c1.price);
        
        // func();

        // dummyFunc();
    }
}
