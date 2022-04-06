import java.util.*;

class Human {
    private int age;
    private double ht;
    private String name;

    Human(String name, int age, double ht) {
        this.name = name;
        this.age = age;
        this.ht = ht;
    }

    public void printDetails() {
        System.out.println(this.getName() + " " + this.getAge() + " " + this.getHeight());
    }

    private double getHeight() {
        return this.ht;
    }

    private String getName() {
        return this.name;
    }

    private int getAge() {
        return this.age;
    }
}

class Car implements Comparable<Car> {
    private String name;
    private int price;
    private double avg;

    public Car() {
        // System.out.println("Welcome in Car class");
    }   

    public int compareTo(Car o) {
        // deciding factor -> price
        return this.price - o.price;
        // +1 -> 'this' is greater
        // -1 -> 'this' is smaller
        // 0 -> both are equal
    }

    public Car(String name, int price, int avg) {
        this.name = name;
        this.price = price;
        this.avg = avg;
    }

    // copyb constructor
    public Car(Car c) {
        System.out.println("In Copy Constructor");
        this.name = c.name;
        this.price = c.price;
        this.avg = c.avg;
    }

    public void setDetails(String name, int price, int avg) {
        this.name = name;
        this.price = price;
        this.avg = avg;
    }

    public void printDetails() {
        System.out.println(name + " " + price + " " + avg);
    }

    @Override
    public String toString() {
        String str = "";
        str += this.name + " " + this.price + " " + this.avg;
        return str;
    }
}

class Student {
    String name;
    int cls;
    int rollNo;
    ArrayList<Integer> list;

    Student() {
        this.name = "A";
        this.cls = 10;
        this.rollNo = 1;
        this.list = new ArrayList<>();
    }

    Student(String name, int cls, int rollNo) {
        this();
        this.name = name;
        this.cls = cls;
        this.rollNo = rollNo;
    }

    Student(int[] data) {
        this();
        for(int val : data) {
            list.add(val);
        }
    }

    public void printDetails() {
        System.out.println("Inside class "+ this);
        System.out.println(this.name + " " + this.cls + " " + this.rollNo);
    }
}

public class demo {

    public static void fun5() {
        
        
        Car c1 = new Car("A", 15, 15);
        Car c2 = new Car("B", -17, 16);
        Car c3 = new Car("B", 19, 16);
        Car c4 = new Car("B", 10, 16);
        Car c5 = new Car("B", -12, 16);

        // System.out.println(c5.hashCode());
        System.out.println("Hello " + c5);

        // how we decide which car is greater
        // for greater, we set price of car as varibale to figure out greater

        // Integer c1 = 10;
        // Integer c2 = 20;

        // if(c1.compareTo(c2) > 0) {
        //     System.out.println("Car 1 is greater");
        // } else if(c1.compareTo(c2) < 0){
        //     System.out.println("Car 2 is greater");
        // } else {
        //     System.out.println("Car 1 and Car 2 is equal");
        // }

        Car[] arr = {c1, c2, c3, c4, c5};
        for(Car c : arr) {
            c.printDetails();
        }
        System.out.println();
        Arrays.sort(arr);
        for(Car c : arr) {
            c.printDetails();
        }
    }

    public static void fun4() {
        // Student s1 = new Student("A", 10, 22);
        // s1.printDetails();
        // System.out.println(s1);

        int[] list = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        Student s2 = new Student(list);
        System.out.println(s2.list);
    }

    public static void fun3() {
        Car c1 = new Car();
        c1.setDetails("Creta", 1300000, 15);
        c1.printDetails();

        Car c2 = new Car(c1);
        System.out.println(c1 + " " + c2);
        // reference copy
        // c2 = c1;
        // System.out.println(c1 + " " + c2);
        c2.printDetails();
    }

    public static int count;

    public static void fun2_1() {
        count += 20;
        System.out.println(count);
    }

    public int num;

    public void fun2_2() {
        count += 20;
        num += 10;
        System.out.println(count + " " + num);
    }

    public static void fun2() {
        demo d1 = new demo();
        d1.fun2_2();
        d1.fun2_2();
        demo d2 = new demo();
        d2.fun2_2();

        // demo d3 = new demo();
        // d3.fun2_2();

        // fun2_1();
        // fun2_1();
        // fun2_1();
    }


    public static void fun() {
        Human h1 = new Human("A", 24, 6);
        Human h2 = new Human("B", 22, 5.10);
        Human h3 = new Human("C", 20, 5.7);
        h1.printDetails();
        h2.printDetails();
        h3.printDetails();
        // System.out.println("Ht : " + h1.printDetails()

        LinkedList<Integer> l1 =  new LinkedList<>();
        l1.addFirst(10);
        l1.addFirst(20);
        l1.addFirst(30);
        l1.addFirst(40);

        System.out.println(l1);
        System.out.println(l1.size());
    }
    public static void main(String[] args) {
        // for(String str : args)
        //     System.out.println(str);

        // fun();
        // fun2();
        // fun3();
        // fun4();
        fun5();
    }

}