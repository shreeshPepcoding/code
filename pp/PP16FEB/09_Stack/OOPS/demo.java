import java.util.*;

class Humans {
    // data members
    private String name = "";
    private int age = 0;
    private double ht = 0;  

    // constructor
    public Humans() {
        System.out.println("Hi!, I'm in constructor");
        this.name = "";
        this.age = 0;
        this.ht = 0;
    }

    public Humans(String name, int age, double ht) {
        System.out.println("I'm inside para. constructor");
        this.name = name;
        this.age = age;
        this.ht = ht;
    }

    // member function
    public void printDetails() {
        System.out.println(name + " " + age + " " + ht); 
    }
}

public class demo {

    public static class Student {

    }

    private String name = "a";
    private int std = 5;
    private int marks = 70;

    public static int count = 20;

    public void printInfo() {
        System.out.println(this.name + " " + this.std + " " + this.marks + " " + this.count);
        this.fun();
    }

    public static void fun2() {
        Student s = new Student();


        // printInfo();

        demo d = new demo();
        d.printInfo();
        
        // d.name = "ABC";
        // d.std = 10;
        // d.marks = 95;
        // d.count = 37;
        
        // d.printInfo();

        // demo d2 = new demo();
        // d2.printInfo();
    }

    public static void fun() {
        Humans h1 = new Humans("A", 21, 5.9);
        // h1.name = "A";
        // h1.age = 20;
        // h1.ht = 5.9;

        // h1.printDetails();

        // Humans h2 = new Humans();
    }

    public static void main(String[] args) {
        
        for(String str : args) {
            System.out.println(str);
        }



        // ArrayList<Integer> list = new ArrayList<>();
        // int[] arr = new int[5];
        // arr.length = 10;
        // fun();
        // fun2();
    }
}