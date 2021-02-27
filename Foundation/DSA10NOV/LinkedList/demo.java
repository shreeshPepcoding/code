import java.util.*;

class Car {
    // Data Member
    public String name = "";
    public int maxSpeed = 100;
    public int age = 5;
    public String color = "Black";
    private int AccidentCount = 1;
    public int carRating = 1000 / AccidentCount;

    // methods
    public void printDetails() {
        System.out.println(name + " " + maxSpeed + " " + color);
    }

    public int getAge() {
        return age;
    }

}

public class demo {

    public static int num = 10;

    public int n = 100;

    public void fun() {
        System.out.println("Hello Guys " + n);
        fun2();
    }

    public static void fun2() {
        System.out.println("I m inside fun2");
    }

    public static void main(String[] args) {
        Car c = new Car();
        c.printDetails();

        // for(String var : args) {
        //     System.out.println(var);
        // }

        // demo d = new demo();
        // d.fun();
        // System.out.println(d.num + " " + d.n);
        // d.num = 15;
        // d.n = 200;
        // demo d2 = new demo();
        // System.out.println(d2.num + " " + d2.n);
    }
}